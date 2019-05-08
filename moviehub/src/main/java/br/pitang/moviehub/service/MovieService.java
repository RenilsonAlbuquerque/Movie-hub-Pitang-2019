package br.pitang.moviehub.service;


import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
}
