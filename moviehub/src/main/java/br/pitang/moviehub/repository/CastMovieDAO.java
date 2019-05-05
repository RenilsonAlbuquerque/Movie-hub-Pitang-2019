package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.CastID;
import br.pitang.moviehub.models.CastMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastMovieDAO extends JpaRepository<CastMovie, CastID> {
}
