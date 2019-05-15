package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.CrewMovie;
import br.pitang.moviehub.models.embedded.CrewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMovieDAO  extends JpaRepository<CrewMovie, CrewId> {
}
