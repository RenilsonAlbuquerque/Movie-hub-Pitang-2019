package br.pitang.moviehub.service;

import br.pitang.moviehub.contracts.services.ISerieService;
import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.mapper.CastMapper;
import br.pitang.moviehub.mapper.SerieMapper;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.models.Serie;
import br.pitang.moviehub.repository.SerieDAO;
import br.pitang.moviehub.specification.ProgramSpecification;
import br.pitang.moviehub.utils.Utils;
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
public class SerieService implements ISerieService {

    @Autowired
    private SerieDAO serieDAO;

    @Autowired
    private SerieMapper serieMapper;

    @Autowired
    private CastMapper castMapper;

    public CustomPage<SerieOverviewDTO> listSeriesOverview(PaginationFilter filter){

        Page<Serie> page = serieDAO.findAll(PageRequest.of(filter.getPage() -1, filter.getSize(),Sort.by("popularity").descending()));
        return (CustomPage<SerieOverviewDTO>) Utils.convertPage(page,
                page
                        .stream() .map( serie -> this.serieMapper.entityToOverview(serie))
                        .collect(Collectors.toList()));
    }

    public SerieDetailDTO findSerieById(Long id) throws ResourceNotFoundException{
        return serieDAO.findById(id)
                .map(serie ->  this.serieMapper.entityToDetail(serie))
                .orElseThrow(()-> new ResourceNotFoundException("A série com id " + id + " não foi encontrada"));
    }
    public CustomPage<SerieOverviewDTO> searchSerie(HashMap<String, Object> params, PaginationFilter filter){
        Specification<Program> specification = ProgramSpecification.searchProgram(params);

        Page<Program> page = this.serieDAO.findAll(specification,PageRequest.of(filter.getPage() -1,
                filter.getSize(),Sort.by("popularity").descending()));
        return (CustomPage<SerieOverviewDTO>) Utils.convertPage(page,
                page
                        .stream() .map( serie -> this.serieMapper.entityToOverview(serie))
                        .collect(Collectors.toList()));
    }
    public List<CastDTO> castOfSerie(long serieId) throws ResourceNotFoundException{
        Serie search =serieDAO.findById(serieId)
                .orElseThrow(()-> new ResourceNotFoundException("A série com id " + serieId + " não foi encontrada"));
        return search.getCast().stream().
                map(cast -> this.castMapper.entityToOverview(cast))
                .collect(Collectors.toList());
    }

    @Override
    public SerieCreationDTO edit(long id, SerieCreationDTO input) throws ResourceNotFoundException {
        Serie search = serieDAO.findById(id)
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
        serieDAO.save(search);
        return input;
    }


}
