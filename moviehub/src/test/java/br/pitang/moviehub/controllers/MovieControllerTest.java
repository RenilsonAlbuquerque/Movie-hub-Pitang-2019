package br.pitang.moviehub.controllers;


import br.pitang.moviehub.dto.CustomPage;
import br.pitang.moviehub.dto.MovieDetailDTO;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.mapper.CastMapper;
import br.pitang.moviehub.mapper.MovieMapper;
import br.pitang.moviehub.service.MovieService;

import br.pitang.moviehub.utils.MovieGenerators;
import br.pitang.moviehub.utils.PaginationGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.stream.Collectors;

import static br.pitang.moviehub.utils.MovieGenerators.generateMovies;
import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/*
@RunWith(SpringRunner.class)
@WebMvcTest(MovieControllerTest.class)
@AutoConfigureMockMvc
*/


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {


    @Mock
    private MovieService movieService;

    @Mock
    private CastMapper castMapper;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private MovieMapper movieMapper;

    private CustomPage<MovieOverviewDTO> moviesInService;







    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        moviesInService = (CustomPage<MovieOverviewDTO>) PaginationGenerator.createPage(generateMovies(10));
        //Whitebox.setInternalState(this.mockMvc,"movieMapper",this.movieMapper);
    }

    @Test
    public void listAllOverviewTest() throws Exception {
        CustomPage<MovieOverviewDTO> serviceFake = (CustomPage<MovieOverviewDTO>) PaginationGenerator.createPage(MovieGenerators.generateMovies(3).stream()
                .map(cast -> movieMapper.entityToOverview(cast)).collect(Collectors.toList()));

        PaginationFilter filter = PaginationFilter.builder().page(1).size(10).build();

        Mockito.when(movieService.listMoviesOverview(Mockito.any(PaginationFilter.class)))
                .thenReturn(serviceFake);

        MockHttpServletResponse response = mockMvc.perform(
                post("/movie")
                        .content(objectMapper.writeValueAsString(filter))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(response.getStatus(),HttpStatus.OK.value());

        CustomPage<MovieOverviewDTO> requestResult =
                this.objectMapper.readValue(response.getContentAsString(),CustomPage.class);

        //assertEquals(serviceFake.getElements().size(),requestResult.getElements().size() );

    }

    @Test
    public void findById() throws Exception {

        Mockito.when(movieService.findMovieById(Mockito.anyLong()))
                .thenReturn(this.movieMapper.entityToDetail(MovieGenerators.generateMovie()));

        MockHttpServletResponse response = mockMvc.perform(
                get("/movie/1"))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());

        MovieDetailDTO requestResult = this.objectMapper.readValue(response.getContentAsString(),MovieDetailDTO.class);

        assertEquals(Long.valueOf(1), (Long) requestResult.getId());

    }
    @Test
    public void getCast() throws Exception {

        /*
        Mockito.when(movieService.castOfMovie(Mockito.anyLong()))
                .thenReturn(MovieGenerators.generateMovieCast(3).stream()
                        .map(cast -> castMapper.entityToOverview(cast)).collect(Collectors.toList()));
        */

        MockHttpServletResponse response = mockMvc.perform(
                get("/movie/cast/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }
}
