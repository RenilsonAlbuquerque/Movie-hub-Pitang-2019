package br.pitang.moviehub.controllers;

import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.SerieDetailDTO;
import br.pitang.moviehub.dto.SerieOverviewDTO;
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


@Slf4j
@RestController
@Api(value = "Serie")
@RequestMapping("serie")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<SerieOverviewDTO>> listAllOverview(@RequestBody PaginationFilter filter){
        return new ResponseEntity<Page<SerieOverviewDTO>>(serieService.listAllSeriesCover(filter), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SerieDetailDTO> findOneById(@PathVariable long id){
        return new ResponseEntity<SerieDetailDTO>(serieService.findOneById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A série com id " + id + " não foi encontrada")), HttpStatus.OK);
    }

}
