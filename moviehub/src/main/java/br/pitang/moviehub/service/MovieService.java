package br.pitang.moviehub.service;


import br.pitang.moviehub.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieDAO movieDAO;
}
