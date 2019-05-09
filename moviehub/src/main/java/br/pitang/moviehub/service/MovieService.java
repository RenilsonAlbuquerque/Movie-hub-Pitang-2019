package br.pitang.moviehub.service;


import br.pitang.moviehub.dto.MovieDetailDTO;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.SerieDetailDTO;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieDAO movieDAO;

    public Page<MovieOverviewDTO> listAllSeriesCover(PaginationFilter filter){

        return new PageImpl<MovieOverviewDTO>(movieDAO.findAll(PageRequest.of(filter.getPage(), filter.getSize())).stream()
                .map( movie -> MovieOverviewDTO.builder()
                        .id(movie.getId())
                        .title(movie.getTitle())
                        .backdropPath(movie.getBackdropPath())
                        .build())
                .collect(Collectors.toList()));
    }
    public Optional<MovieDetailDTO> findOneById(Long id) throws ResourceNotFoundException{
        return movieDAO.findById(id)
                .map(movie -> MovieDetailDTO.builder()
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
                        .build()
                );
    }
}
