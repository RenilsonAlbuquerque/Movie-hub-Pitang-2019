package br.pitang.moviehub.repository;

import br.pitang.moviehub.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserDAO extends PagingAndSortingRepository<User, Long> {


    Optional<User> findByUsername(String username);
}
