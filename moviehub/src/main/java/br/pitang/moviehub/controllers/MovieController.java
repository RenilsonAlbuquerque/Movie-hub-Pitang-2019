package br.pitang.moviehub.controllers;

import br.pitang.moviehub.contracts.services.IMovieService;
import br.pitang.moviehub.dto.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    private IMovieService movieService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomPage<MovieOverviewDTO>> listAllOverview(@RequestBody PaginationFilter filter){
        return new ResponseEntity<CustomPage<MovieOverviewDTO>>(movieService.listMoviesOverview(filter), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MovieDetailDTO> findOneById(@PathVariable long id){
        return new ResponseEntity<MovieDetailDTO>(movieService.findMovieById(id), HttpStatus.OK);
    }
    @PostMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomPage<MovieOverviewDTO>> filter(@RequestBody PaginationFilter filter,
    													@RequestParam(required = false) String title,
    													@RequestParam(required = false) Integer year,
    													@RequestParam(required = false) String language){
    	HashMap<String,Object> queryParams = new HashMap<String,Object>();
    	if(title != null) {queryParams.put("title", title);}
    	if(year != null) {queryParams.put("releaseYear", year);}
    	if(language != null) {queryParams.put("language", language);}
    	
    	 return new ResponseEntity<CustomPage
    			 <MovieOverviewDTO>>(movieService.searchMovie(queryParams,filter), HttpStatus.OK);
    }
    @GetMapping(value = "/cast/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CastDTO>> getCast(@PathVariable long id){
        return new ResponseEntity<List<CastDTO>>(movieService.castOfMovie(id), HttpStatus.OK);
    }
    @PostMapping(value = "/update/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MovieCreationDTO> updateMovie(@RequestParam long id,
                                                        @RequestBody MovieCreationDTO input){
        return new ResponseEntity<MovieCreationDTO>(movieService.edit(id,input), HttpStatus.OK);
    }

}
