package br.pitang.moviehub.dto;

import java.io.Serializable;
import java.util.List;

import br.pitang.moviehub.dto.SerieDetailDTO.SerieDetailDTOBuilder;
import br.pitang.moviehub.models.GenereMovie;
import br.pitang.moviehub.models.GenereSerie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetailDTO implements Serializable{
	
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
    private String tagline;
    private List<GenereMovie> generes; 

}
