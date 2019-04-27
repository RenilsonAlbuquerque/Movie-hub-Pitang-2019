package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonDAO extends JpaRepository<Long, Season> {
}
