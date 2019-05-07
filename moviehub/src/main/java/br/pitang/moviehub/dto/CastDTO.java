package br.pitang.moviehub.dto;


import br.pitang.moviehub.models.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CastDTO {

    private String role;
    private Person actor;

}
