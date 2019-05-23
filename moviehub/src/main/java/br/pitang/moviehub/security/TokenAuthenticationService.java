package br.pitang.moviehub.security;


import br.pitang.moviehub.models.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class TokenAuthenticationService {

    static final long EXPIRATION_TIME = 860_000_000; //10 DAYS IN MILLIS
    static final String SECRET_KEY = "M0vi3Hub";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";


    static void addAuthentication(HttpServletResponse response, Long id, String username, Collection<? extends GrantedAuthority> autorizations) {

        ArrayList<String> rolesAsString = new ArrayList<>();
        for ( Role role : (Collection<Role>)autorizations ) {
            rolesAsString.add(role.getAuthority());
        }

        String JWT = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .claim("id", id)
                .claim("username", username)
                .claim("roles", rolesAsString)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    static Authentication getAuthentication(HttpServletRequest request) throws SignatureException, BadCredentialsException {
        String token = request.getHeader(HEADER_STRING);

        Claims claims = null;
        if(token != null) {
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            String user = (String) claims.get("username");

            ArrayList<Role> roles = new ArrayList<>();
            /*
            ArrayList<String> claimsRoles = (ArrayList<String>) claims.get("roles");
            for (String s : claimsRoles) {
                TypeRole role = TypeRole.valueOf(s);
                roles.add(staticRoleRepository.findAllByType(role));
            }
             */
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, roles);
            }
        }
        return null;
    }

}
