package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Movie;
import br.pitang.moviehub.models.Program;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieDAO extends JpaRepository< Movie,Long>, JpaSpecificationExecutor<Program> {
}
