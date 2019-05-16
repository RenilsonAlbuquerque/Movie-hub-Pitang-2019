package br.pitang.moviehub.mapper;

import br.pitang.moviehub.dto.SerieDetailDTO;
import br.pitang.moviehub.dto.SerieOverviewDTO;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.models.Serie;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SerieMapper {

    public SerieDetailDTO entityToDetail(Serie serie){
        return new SerieDetailDTO().builder()
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
                .build();
    }
    public SerieOverviewDTO entityToOverview(Serie serie){
        return SerieOverviewDTO.builder()
                .id(serie.getId())
                .title(serie.getTitle())
                .backdropPath(serie.getBackdropPath())
                .build();
    }
    public SerieOverviewDTO entityToOverview(Program program){
        return SerieOverviewDTO.builder()
                .id(program.getId())
                .title(program.getTitle())
                .backdropPath(program.getBackdropPath())
                .build();
    }
}
