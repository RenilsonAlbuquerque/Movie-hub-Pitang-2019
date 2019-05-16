package br.pitang.moviehub.dto;

import br.pitang.moviehub.models.GenereMovie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SerieCreationDTO {

    private Long id;
    private String title;
    private String description;
    private String country;
    private String language;
    private Long releaseYear;
    private double durationInMinutes;
    private Double voteAverage;
    private Long voteCount;
    private String backdropPath;
    private List<GenereMovie> generes;
    private List<Integer> cast;
    private List<Integer> crew;
    private List<Integer> seasons;
}
