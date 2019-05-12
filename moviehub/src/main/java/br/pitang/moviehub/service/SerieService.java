package br.pitang.moviehub.service;

import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.SerieDetailDTO;
import br.pitang.moviehub.dto.SerieOverviewDTO;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Serie;
import br.pitang.moviehub.repository.SerieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class SerieService {

    @Autowired
    private SerieDAO serieDAO;

    public Page<SerieOverviewDTO> listAllSeriesCover(PaginationFilter filter){

        return new PageImpl<SerieOverviewDTO> (serieDAO.findAll(PageRequest.of(filter.getPage(), filter.getSize(),Sort.by("popularity").descending())).stream()
                .map( serie -> SerieOverviewDTO.builder()
                        .id(serie.getId())
                        .title(serie.getTitle())
                        .backdropPath(serie.getBackdropPath())
                        .build())
                .collect(Collectors.toList()));
    }

    public Optional<SerieDetailDTO> findOneById(Long id) throws ResourceNotFoundException{
        return serieDAO.findById(id)
                .map(serie -> SerieDetailDTO.builder()
                        .id(serie.getId())
                        .title(serie.getTitle())
                        .description(serie.getDescription())
                        .country(serie.getCountry())
                        .language(serie.getLanguage())
                        .releaseYear(serie.getReleaseYear())
                        .durationInMinutes(serie.getDurationInMinutes())
                        .voteAverage(serie.getVoteAverage())
                        .voteCount(serie.getVoteCount())
                        .backdropPath(serie.getBackdropPath())
                        .generes(serie.getGeneres())
                        .seasons(serie.getSeasons().stream().map( season -> season.getId()).collect(Collectors.toList()))
                        .build()
                );
    }


}
