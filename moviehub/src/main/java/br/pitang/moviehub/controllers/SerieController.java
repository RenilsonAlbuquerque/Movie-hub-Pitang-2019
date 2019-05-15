package br.pitang.moviehub.controllers;

import br.pitang.moviehub.contracts.services.ISerieService;
import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.service.SerieService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@Api(value = "Serie")
@RequestMapping("serie")
public class SerieController {

    @Autowired
    private ISerieService serieService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomPage<SerieOverviewDTO>> listAllOverview(@RequestBody PaginationFilter filter){
        return new ResponseEntity<CustomPage<SerieOverviewDTO>>(serieService.listSeriesOverview(filter), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SerieDetailDTO> findOneById(@PathVariable long id){
        return new ResponseEntity<SerieDetailDTO>(serieService.findSerieById(id), HttpStatus.OK);
    }
    @PostMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomPage<SerieOverviewDTO>> filter(@RequestBody PaginationFilter filter,
                                                               @RequestParam(required = false) String title,
                                                               @RequestParam(required = false) Integer year,
                                                               @RequestParam(required = false) String language){
        HashMap<String,Object> queryParams = new HashMap<String,Object>();
        if(title != null) {queryParams.put("title", title);}
        if(year != null) {queryParams.put("releaseYear", year);}
        if(language != null) {queryParams.put("language", language);}

        return new ResponseEntity<CustomPage
                <SerieOverviewDTO>>(serieService.searchSerie(queryParams,filter), HttpStatus.OK);
    }
    @GetMapping(value = "/cast/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CastDTO>> getCast(@PathVariable long id){
        return new ResponseEntity<List<CastDTO>>(serieService.castOfSerie(id), HttpStatus.OK);
    }

}
