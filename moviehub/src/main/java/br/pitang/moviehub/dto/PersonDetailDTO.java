package br.pitang.moviehub.dto;

import br.pitang.moviehub.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDetailDTO {


    private Long id;
    private String name;
    private String biography;
    private Integer height;
    private String birthCity;
    private String countryWhereLive;
    private Genre genre;
    private Double popularity;
    private String profilePiturePath;
}
