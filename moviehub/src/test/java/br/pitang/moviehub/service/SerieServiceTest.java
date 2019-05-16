package br.pitang.moviehub.service;

import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Serie;
import br.pitang.moviehub.repository.SerieDAO;
import br.pitang.moviehub.utils.SeriesGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class SerieServiceTest {

    @InjectMocks
    private SerieService serieService;

    @Mock
    private SerieDAO serieDAO;

    private Page<Serie> seriesInRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        seriesInRepository = new PageImpl<>(SeriesGenerator.generateSeries(10));
        Whitebox.setInternalState(serieService, "serieDAO",serieDAO );
    }
    @Test
    public void listSeriesOverviewTest(){
        PaginationFilter filter = new PaginationFilter(1,10);
        Mockito.when(serieDAO.findAll(Mockito.any(PageRequest.class)))
                .thenReturn(seriesInRepository);
        assertEquals(9L, serieService.listSeriesOverview(filter).getElements().size());
    }
    @Test
    public void findSerieByValidId(){
        Mockito.when(serieDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(seriesInRepository.getContent().get(0)));
        SerieDetailDTO found = serieService.findSerieById(1L);
        assertEquals(Long.valueOf(1), found.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findSerieByInexistentId(){
        Mockito.when(serieDAO.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        serieService.findSerieById(1L);
    }
    @Test
    public void searchMovieByName(){
        Mockito.when(serieDAO.findAll(Mockito.any(Specification.class),Mockito.any(PageRequest.class)))
                .thenReturn(seriesInRepository);
        PaginationFilter filter = new PaginationFilter(1,10);
        HashMap<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("title", "Avengers");

        CustomPage<SerieOverviewDTO> output = serieService.searchSerie(queryParams,filter);
        assertEquals(9,output.getElements().size());
    }
    @Test
    public void searchCastTest(){
        Mockito.when(serieDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(seriesInRepository.getContent().get(0)));
        List<CastDTO> found = serieService.castOfSerie(1L);
        assertEquals(2L, found.size());
    }
    @Test
    public void editSerieTest(){
        Mockito.when(serieDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(seriesInRepository.getContent().get(0)));

        SerieCreationDTO input = SerieCreationDTO.builder().title("Serie A").build();
        SerieCreationDTO edited = serieService.edit(1L,input);
        assertEquals("Serie A", edited.getTitle());
    }
    @Test(expected = ResourceNotFoundException.class)
    public void editSerieWithInexistentId(){
        Mockito.when(serieDAO.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        SerieCreationDTO input = SerieCreationDTO.builder().title("Serie A").build();
        serieService.edit(1L,input);
    }
}
