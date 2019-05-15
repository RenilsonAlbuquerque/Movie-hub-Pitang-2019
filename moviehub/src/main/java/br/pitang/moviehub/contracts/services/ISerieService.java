package br.pitang.moviehub.contracts.services;

import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface ISerieService {
    CustomPage<SerieOverviewDTO> listSeriesOverview(PaginationFilter filter);
    SerieDetailDTO findSerieById(Long id) throws ResourceNotFoundException;
    CustomPage<SerieOverviewDTO> searchSerie(HashMap<String, Object> params, PaginationFilter filter);
    List<CastDTO> castOfSerie(long movieId) throws ResourceNotFoundException;
}
