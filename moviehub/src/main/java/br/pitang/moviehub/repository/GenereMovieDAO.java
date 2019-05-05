package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.GenereMovie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenereMovieDAO extends JpaRepository<GenereMovie,Long> {

    Optional<GenereMovie> findById(Long id);
}
