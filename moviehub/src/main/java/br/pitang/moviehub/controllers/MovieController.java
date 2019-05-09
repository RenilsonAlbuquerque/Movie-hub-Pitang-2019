package br.pitang.moviehub.controllers;

import br.pitang.moviehub.dto.MovieDetailDTO;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.SerieDetailDTO;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.service.MovieService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@Api(value = "Movie")
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<MovieOverviewDTO>> listAllOverview(@RequestBody PaginationFilter filter){
        return new ResponseEntity<Page<MovieOverviewDTO>>(movieService.listAllSeriesCover(filter), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MovieDetailDTO> findOneById(@PathVariable long id){
        return new ResponseEntity<MovieDetailDTO>(movieService.findOneById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O filme com id " + id + " não foi encontrado")), HttpStatus.OK);
    }
}
