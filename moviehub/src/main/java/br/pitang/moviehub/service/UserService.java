package br.pitang.moviehub.service;


import br.pitang.moviehub.models.User;
import br.pitang.moviehub.repository.UserDAO;
import br.pitang.moviehub.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserDAO userDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userDAO.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException(Messages.USER_NOT_FOUND));
    }

    public Boolean createUser(User user){
        user.setPassword(this.encrypt(user.getPassword()));
        this.userDAO.save(user);
        return true;
    }

    private String encrypt(String password){
        return password;
    }
}
