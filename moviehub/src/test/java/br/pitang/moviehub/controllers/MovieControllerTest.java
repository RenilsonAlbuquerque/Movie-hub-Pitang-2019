package br.pitang.moviehub.controllers;

import br.pitang.moviehub.dto.CustomPage;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.service.MovieService;

import br.pitang.moviehub.utils.Utils;
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


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);


        moviesInService = (CustomPage<MovieOverviewDTO>) Utils.createPage(generateMovies(10));
    }

    @Test
    public void findById() throws Exception {

        Mockito.when(movieService.listMoviesOverview(Mockito.any(PaginationFilter.class)))
                .thenReturn(moviesInService);

        PaginationFilter filter = PaginationFilter.builder().page(1).size(10).build();
        MockHttpServletResponse response = mockMvc.perform(
                post("/movie")
                        .content(objectMapper.writeValueAsString(filter))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(),HttpStatus.OK.value());

    }
}
