package br.pitang.moviehub.service;


import br.pitang.moviehub.dto.CustomPage;
import br.pitang.moviehub.dto.MovieDetailDTO;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Movie;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.repository.MovieDAO;
import br.pitang.moviehub.specification.ProgramSpecification;
import br.pitang.moviehub.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class MovieService {

    @Autowired
    private MovieDAO movieDAO;

    @SuppressWarnings("unchecked")
	public CustomPage<MovieOverviewDTO> listAllSeriesCover(PaginationFilter filter){
    
    	Page<Movie> page = movieDAO.findAll(PageRequest.of(filter.getPage() -1, filter.getSize(),Sort.by("popularity").descending()));
        return (CustomPage<MovieOverviewDTO>) Utils.convertPage(page,
        		page
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
    
    @SuppressWarnings("unchecked")
	public CustomPage<MovieOverviewDTO> searchMovie(HashMap<String, Object> params,PaginationFilter filter){
    	Specification<Program> specification = ProgramSpecification.searchProgram(params);
    
    	Page<Program> page = this.movieDAO.findAll(specification,PageRequest.of(filter.getPage() -1, 
				filter.getSize(),Sort.by("popularity").descending()));
    	return (CustomPage<MovieOverviewDTO>) Utils.convertPage(page,
        		page
        		.stream().map( movie -> MovieOverviewDTO.builder()
                        .id(movie.getId())
                        .title(movie.getTitle())
                        .backdropPath(movie.getBackdropPath())
                        .build())
                .collect(Collectors.toList()));
    }
}
