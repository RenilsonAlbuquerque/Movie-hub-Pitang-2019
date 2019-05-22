package br.pitang.moviehub.service;



import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.mapper.CastMapper;
import br.pitang.moviehub.mapper.MovieMapper;
import br.pitang.moviehub.models.Movie;
import br.pitang.moviehub.repository.MovieDAO;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.util.reflection.Whitebox;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import br.pitang.moviehub.utils.MovieGenerators;


import java.util.HashMap;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class MovieServiceTest {


    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieDAO movieDAO;

    @InjectMocks
    private MovieMapper movieMapper;

    @Mock
    private CastMapper castMapper;


    private Page<Movie> moviesInRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        moviesInRepository = new PageImpl<>(MovieGenerators.generateMovies(10));
        Whitebox.setInternalState(this.movieService, "movieDAO",movieDAO );
        Whitebox.setInternalState(this.movieService,"movieMapper",this.movieMapper);
        Whitebox.setInternalState(this.movieService,"castMapper",this.castMapper);
    }


    @Test
    public void listMoviesOverviewTest(){
        PaginationFilter filter = new PaginationFilter(1,10);
        Mockito.when(movieDAO.findAll(Mockito.any(PageRequest.class)))
                .thenReturn(moviesInRepository);
        assertEquals(9,movieService.listMoviesOverview(filter).getElements().size());
    }

    @Test
    public void findMovieByValidId(){
        Optional<Movie> fake = Optional.of(this.moviesInRepository.getContent().get(0));
        Mockito.when(movieDAO.findById(Mockito.anyLong())).thenReturn(fake);
        MovieDetailDTO found = movieService.findMovieById(1L);
        assertEquals(Long.valueOf(1), found.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findMovieByInexistentId(){
        Mockito.when(movieDAO.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        movieService.findMovieById(1L);
    }

    @Test
    public void searchMovieByName(){
        Mockito.when(movieDAO.findAll(Mockito.any(Specification.class),Mockito.any(PageRequest.class)))
                .thenReturn(moviesInRepository);
        PaginationFilter filter = new PaginationFilter(1,10);
        HashMap<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("title", "Avengers");

        CustomPage<MovieOverviewDTO> output = movieService.searchMovie(queryParams,filter);
        assertEquals(9,output.getElements().size());
    }
    @Test
    public void searchCastTest(){
        Mockito.when(movieDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(moviesInRepository.getContent().get(0)));


        //Mockito.doReturn(Optional.of(moviesInRepository.getContent().get(0))).when(movieDAO.findById(Mockito.anyLong()));
        List<CastDTO> found = movieService.castOfMovie(1L);
        assertEquals(2L, found.size());
    }
    @Test
    public void editTest(){
        Mockito.when(movieDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(moviesInRepository.getContent().get(0)));

        MovieCreationDTO input = MovieCreationDTO.builder().title("Movie A").build();
        MovieCreationDTO edited = movieService.edit(1L,input);
        assertEquals("Movie A", edited.getTitle());
    }
    @Test(expected = ResourceNotFoundException.class)
    public void editMovieWithInexistentId(){
        Mockito.when(movieDAO.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        MovieCreationDTO input = MovieCreationDTO.builder().title("Movie A").build();
        movieService.edit(1L,input);
    }
}
