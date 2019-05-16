package br.pitang.moviehub.controllers;

import br.pitang.moviehub.dto.CustomPage;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.stream.Collectors;

import static br.pitang.moviehub.utils.MovieGenerators.generateMovies;
import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieControllerTest.class)
public class MovieControllerTest {


    @Mock
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    private CustomPage<MovieOverviewDTO> moviesInService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Mock
    private CastMapper castMapper;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        moviesInService = (CustomPage<MovieOverviewDTO>) PaginationGenerator.createPage(generateMovies(10));
    }

    @Test
    public void findById() throws Exception {

        Mockito.when(movieService.findMovieById(Mockito.anyLong()))
                .thenReturn(this.movieMapper.entityToDetail(MovieGenerators.generateMovies(1).get(0)));

        PaginationFilter filter = PaginationFilter.builder().page(1).size(10).build();
        MockHttpServletResponse response = mockMvc.perform(
                post("/movie")
                        .content(objectMapper.writeValueAsString(filter))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(),HttpStatus.OK.value());

    }
    @Test
    public void getCast() throws Exception {

        Mockito.when(movieService.castOfMovie(Mockito.anyLong()))
                .thenReturn(MovieGenerators.generateMovieCast(3).stream()
                        .map(cast -> castMapper.entityToOverview(cast)).collect(Collectors.toList()));


        MockHttpServletResponse response = mockMvc.perform(
                post("/movie//cast/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(),HttpStatus.OK.value());

    }
}
