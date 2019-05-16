package br.pitang.moviehub.mapper;

import br.pitang.moviehub.dto.MovieDetailDTO;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.models.Movie;
import br.pitang.moviehub.models.Program;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {
    public MovieDetailDTO entityToDetail(Movie movie){
        return new MovieDetailDTO().builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .country(movie.getCountry())
                .language(movie.getLanguage())
                .releaseYear(movie.getReleaseYear())
                .durationInMinutes(movie.getDurationInMinutes())
                .voteAverage(movie.getVoteAverage())
                .voteCount(movie.getVoteCount())
                .backdropPath(movie.getBackdropPath())
                .generes(movie.getGeneres())
                .build();
    }
    public MovieOverviewDTO entityToOverview(Movie movie){
        return MovieOverviewDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .backdropPath(movie.getBackdropPath())
                .build();
    }
    public MovieOverviewDTO entityToOverview(Program program){
        return MovieOverviewDTO.builder()
                .id(program.getId())
                .title(program.getTitle())
                .backdropPath(program.getBackdropPath())
                .build();
    }
}
