package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.GenereSerie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenereSerieDAO extends JpaRepository<GenereSerie,Long> {

    Optional<GenereSerie> findById(Long id);


}
