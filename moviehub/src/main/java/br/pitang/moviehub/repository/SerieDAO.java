package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Serie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SerieDAO extends PagingAndSortingRepository<Serie, Long> {
}
