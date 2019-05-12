package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.Person;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonDAO extends JpaRepository<Person,Long>, JpaSpecificationExecutor<Person> {
}
