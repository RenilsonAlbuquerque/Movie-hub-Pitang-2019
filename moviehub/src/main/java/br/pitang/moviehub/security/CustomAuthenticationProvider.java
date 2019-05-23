package br.pitang.moviehub.security;

import br.pitang.moviehub.models.User;
import br.pitang.moviehub.service.UserService;
import br.pitang.moviehub.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            UserDetails user = this.userService.loadUserByUsername(username);
            if (user.getPassword().equals(password)) {
                return new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        password,
                        user.getAuthorities()
                );
            }else{
                throw new BadCredentialsException(Messages.INVALID_PASSWORD);
            }

        }catch(UsernameNotFoundException e){
            throw new BadCredentialsException(Messages.USER_NOT_FOUND);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
