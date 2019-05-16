package br.pitang.moviehub.service;


import br.pitang.moviehub.contracts.services.IMovieService;
import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.mapper.CastMapper;
import br.pitang.moviehub.mapper.MovieMapper;
import br.pitang.moviehub.models.Movie;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.repository.MovieDAO;
import br.pitang.moviehub.specification.ProgramSpecification;
import br.pitang.moviehub.utils.PaginationGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private CastMapper castMapper;

    @SuppressWarnings("unchecked")
	public CustomPage<MovieOverviewDTO> listMoviesOverview(PaginationFilter filter){
    
    	Page<Movie> page = movieDAO.findAll(PageRequest.of(filter.getPage() -1, filter.getSize(),Sort.by("popularity").descending()));
        return (CustomPage<MovieOverviewDTO>) PaginationGenerator.convertPage(page,
        		page
        		.stream().map( movie -> movieMapper.entityToOverview(movie))
                .collect(Collectors.toList()));
    }
    public MovieDetailDTO findMovieById(Long id) throws ResourceNotFoundException{
        return movieDAO.findById(id).map(movie -> this.movieMapper.entityToDetail(movie))
                .orElseThrow(()-> new ResourceNotFoundException("O filme com id " + id + " não foi encontrado"));
    }
    
    @SuppressWarnings("unchecked")
	public CustomPage<MovieOverviewDTO> searchMovie(HashMap<String, Object> params,PaginationFilter filter){
    	Specification<Program> specification = ProgramSpecification.searchProgram(params);
    
    	Page<Program> page = this.movieDAO.findAll(specification,PageRequest.of(filter.getPage() -1, 
				filter.getSize(),Sort.by("popularity").descending()));
    	return (CustomPage<MovieOverviewDTO>) PaginationGenerator.convertPage(page,
        		page
        		.stream().map( movie -> this.movieMapper.entityToOverview(movie))
                .collect(Collectors.toList()));
    }
    public List<CastDTO> castOfMovie(long movieId) throws ResourceNotFoundException{
        Movie search = movieDAO.findById(movieId)
                .orElseThrow(()-> new ResourceNotFoundException("O filme com id " + movieId + " não foi encontrado"));
        return search.getCast().stream().
                map(cast -> this.castMapper.entityToOverview(cast))
                .collect(Collectors.toList());
    }

    @Override
    public MovieCreationDTO edit(long id, MovieCreationDTO input) throws ResourceNotFoundException {
        Movie search = movieDAO.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("O filme com id " + id + " não foi encontrado"));
        search.setTitle(input.getTitle());
        search.setDescription(input.getDescription());
        search.setCountry(input.getCountry());
        search.setLanguage(input.getLanguage());
        search.setReleaseYear(input.getReleaseYear());
        search.setDurationInMinutes(input.getDurationInMinutes());
        search.setVoteAverage(input.getVoteAverage());
        search.setVoteCount(input.getVoteCount());
        search.setBackdropPath(input.getBackdropPath());
        search.setTagline(input.getTagline());

        movieDAO.save(search);
        return input;

    }
}
