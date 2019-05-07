package br.pitang.moviehub.service;

import br.pitang.moviehub.dto.SerieOverviewDTO;
import br.pitang.moviehub.repository.SerieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import java.util.stream.Collectors;


@Service
public class SerieService {

    @Autowired
    private SerieDAO serieDAO;

    public Page<SerieOverviewDTO> listAllSeriesCover(int page, int size){

        return new PageImpl<SerieOverviewDTO> (serieDAO.findAll(PageRequest.of(page, size)).stream()
                .map( serie -> SerieOverviewDTO.builder()
                        .id(serie.getId())
                        .title(serie.getTitle())
                        .backdropPath(serie.getBackdropPath())
                        .build())
                .collect(Collectors.toList()));
    }


}
