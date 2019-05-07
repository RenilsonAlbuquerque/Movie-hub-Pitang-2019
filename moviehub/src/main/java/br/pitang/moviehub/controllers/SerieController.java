package br.pitang.moviehub.controllers;

import br.pitang.moviehub.dto.SerieOverviewDTO;
import br.pitang.moviehub.service.SerieService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(value = "serie")
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<SerieOverviewDTO>> listAllOverview(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<Page<SerieOverviewDTO>>(serieService.listAllSeriesCover(page,size), HttpStatus.OK);
    }
}
