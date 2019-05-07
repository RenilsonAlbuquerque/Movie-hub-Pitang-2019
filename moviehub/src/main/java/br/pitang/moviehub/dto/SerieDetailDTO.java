package br.pitang.moviehub.dto;


import br.pitang.moviehub.models.CastSerie;
import br.pitang.moviehub.models.CrewSerie;
import br.pitang.moviehub.models.GenereSerie;
import br.pitang.moviehub.models.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SerieDetailDTO implements Serializable {

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
    private List<GenereSerie> generes;
    private List<Long> seasons;
    /*
    private List<CastDTO> cast;
    private List<CrewDTO> crew;
    */
}
