package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.CrewID;
import br.pitang.moviehub.models.CrewMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMovieDAO  extends JpaRepository<CrewMovie, CrewID> {
}
