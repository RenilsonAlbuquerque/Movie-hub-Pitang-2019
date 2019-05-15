package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.embedded.CastId;
import br.pitang.moviehub.models.CastSerie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastSerieDAO extends JpaRepository<CastSerie, CastId> {
}
