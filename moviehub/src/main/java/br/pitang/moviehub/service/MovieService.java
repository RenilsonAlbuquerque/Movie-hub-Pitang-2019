package br.pitang.moviehub.service;


import br.pitang.moviehub.dto.MovieDetailDTO;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.SerieDetailDTO;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Movie;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.repository.MovieDAO;
import br.pitang.moviehub.specification.ProgramSpecification;
import br.pitang.moviehub.specification.SearchCriteria;

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
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

@Service
public class MovieService {

    @Autowired
    private MovieDAO movieDAO;

    public Page<MovieOverviewDTO> listAllSeriesCover(PaginationFilter filter){

        return new PageImpl<MovieOverviewDTO>(
        		movieDAO.findAll(PageRequest.of(filter.getPage(), filter.getSize(),Sort.by("popularity").descending()))
        		.stream().map( movie -> MovieOverviewDTO.builder()
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
    
    public Page<MovieOverviewDTO> searchMovie(HashMap<String, Object> params,PaginationFilter filter){
    	Specification<Program> specification = ProgramSpecification.searchProgram(params);
    
    	
    	return new PageImpl<MovieOverviewDTO>(
    			this.movieDAO.findAll(specification,PageRequest.of(filter.getPage(), 
    									filter.getSize())).stream()
    			.map( movie -> MovieOverviewDTO.builder()
                        .id(movie.getId())
                        .title(movie.getTitle())
                        .backdropPath(movie.getBackdropPath())
                        .build())
                .collect(Collectors.toList()));
    }
}
