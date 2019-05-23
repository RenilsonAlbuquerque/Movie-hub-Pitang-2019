package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {


    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
