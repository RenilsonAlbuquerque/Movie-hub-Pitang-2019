package br.pitang.programhub.repository;

import br.pitang.programhub.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieDAO extends JpaRepository<Serie, Long> {
}
