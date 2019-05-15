package br.pitang.moviehub.contracts.services;

import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;


public interface IMovieService {

    CustomPage<MovieOverviewDTO> listAllSeriesCover(PaginationFilter filter);
    MovieDetailDTO findMovieById(Long id) throws ResourceNotFoundException;
    CustomPage<MovieOverviewDTO> searchMovie(HashMap<String, Object> params, PaginationFilter filter);
    List<CastDTO> castOfMovie(long movieId) throws ResourceNotFoundException;
}
