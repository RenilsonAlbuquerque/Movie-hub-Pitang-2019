package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<Long, Movie> {
}
