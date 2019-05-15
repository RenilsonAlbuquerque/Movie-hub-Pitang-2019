package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.CrewSerie;
import br.pitang.moviehub.models.embedded.CrewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewSerieDAO extends JpaRepository<CrewSerie, CrewId> {
}
