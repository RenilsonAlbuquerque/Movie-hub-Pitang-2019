package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieDAO extends JpaRepository<Long, Serie> {
}
