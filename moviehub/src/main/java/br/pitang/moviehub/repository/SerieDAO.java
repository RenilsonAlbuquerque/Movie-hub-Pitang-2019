package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.models.Serie;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SerieDAO extends PagingAndSortingRepository<Serie, Long>, JpaSpecificationExecutor<Program> {
}
