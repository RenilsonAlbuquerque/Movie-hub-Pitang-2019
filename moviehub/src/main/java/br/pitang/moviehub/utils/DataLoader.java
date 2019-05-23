package br.pitang.moviehub.utils;

import br.pitang.moviehub.models.User;
import br.pitang.moviehub.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserDAO userRepository;

    @Autowired
    public DataLoader(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        //this.userRepository.save(User.builder().id(1L).username("Re").password("1234").activated(true).build());
    }
}
