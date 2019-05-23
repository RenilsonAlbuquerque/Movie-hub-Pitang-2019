package br.pitang.moviehub.security;

import br.pitang.moviehub.utils.Messages;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthoriationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;
        try{
            Authentication authentication = TokenAuthenticationService
                    .getAuthentication((HttpServletRequest) request);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }catch (SignatureException ex) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, Messages.INVALID_TOKEN);
        }catch (MalformedJwtException ex1) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, Messages.INVALID_TOKEN_FORMAT);
        }catch (BadCredentialsException ex2) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex2.getMessage());
        }catch (Exception ex3) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex3.getMessage());
        }
    }
}
