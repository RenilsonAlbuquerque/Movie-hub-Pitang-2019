package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Genere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenereDAO extends JpaRepository<Long, Genere> {

}
