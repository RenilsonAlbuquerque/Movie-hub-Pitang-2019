package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.embedded.CrewSerieID;
import br.pitang.moviehub.models.CrewSerie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewSerieDAO extends JpaRepository<CrewSerie, CrewSerieID> {
}
