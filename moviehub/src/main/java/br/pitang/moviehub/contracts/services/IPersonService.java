package br.pitang.moviehub.contracts.services;

import br.pitang.moviehub.dto.CustomPage;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.PersonDetailDTO;
import br.pitang.moviehub.dto.PersonOverviewDTO;
import br.pitang.moviehub.exception.ResourceNotFoundException;

import java.util.HashMap;

public interface IPersonService {

    CustomPage<PersonOverviewDTO> listPersonOverview(PaginationFilter filter);
    PersonDetailDTO findPersonById(Long id) throws ResourceNotFoundException;
    CustomPage<PersonOverviewDTO> filterPerson(HashMap<String, Object> params, PaginationFilter filter);
}
