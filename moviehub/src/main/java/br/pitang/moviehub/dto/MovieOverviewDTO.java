package br.pitang.moviehub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieOverviewDTO implements Serializable {

    private Long id;
    private String title;
    private String backdropPath;

}
