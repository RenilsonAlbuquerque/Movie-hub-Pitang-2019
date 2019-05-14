package br.pitang.moviehub.service;

import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.models.Serie;
import br.pitang.moviehub.repository.SerieDAO;
import br.pitang.moviehub.specification.ProgramSpecification;
import br.pitang.moviehub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class SerieService {

    @Autowired
    private SerieDAO serieDAO;

    public CustomPage<SerieOverviewDTO> listAllSeriesCover(PaginationFilter filter){

        Page<Serie> page = serieDAO.findAll(PageRequest.of(filter.getPage() -1, filter.getSize(),Sort.by("popularity").descending()));
        return (CustomPage<SerieOverviewDTO>) Utils.convertPage(page,
                page
                        .stream() .map( serie -> SerieOverviewDTO.builder()
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
    public CustomPage<SerieOverviewDTO> searchSerie(HashMap<String, Object> params, PaginationFilter filter){
        Specification<Program> specification = ProgramSpecification.searchProgram(params);

        Page<Program> page = this.serieDAO.findAll(specification,PageRequest.of(filter.getPage() -1,
                filter.getSize(),Sort.by("popularity").descending()));
        return (CustomPage<SerieOverviewDTO>) Utils.convertPage(page,
                page
                        .stream() .map( serie -> SerieOverviewDTO.builder()
                        .id(serie.getId())
                        .title(serie.getTitle())
                        .backdropPath(serie.getBackdropPath())
                        .build())
                        .collect(Collectors.toList()));
    }
    public List<CastDTO> listCastOfSerie(long serieId){
        Serie serieSearch =serieDAO.findById(serieId)
                .orElseThrow(()-> new ResourceNotFoundException("A série com id " + serieId + " não foi encontrada"));
        return serieSearch.getCast().stream().
                map(cast -> CastDTO.builder()
                        .character(cast.getCharacter())
                        .actor(cast.getPerson().getName())
                        .profilePicture(cast.getPerson().getProfilePiturePath())
                .build())
                .collect(Collectors.toList());
    }


}
