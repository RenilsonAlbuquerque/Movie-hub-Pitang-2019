package br.pitang.moviehub.utils;

import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.models.CastMovie;
import br.pitang.moviehub.models.Movie;
import br.pitang.moviehub.models.Person;

import java.util.ArrayList;
import java.util.List;

public class MovieGenerators {

    public static Movie generateMovie(){
         return Movie.builder()
                    .id(1L)
                    .title("O auto da compadecida")
                    .description("Chicó e João Grillo aprontando altas confusões em Taperoá")
                    .country("Brasil")
                    .language("Português")
                    .releaseyear(2019L)
                    .durationInMinutes(181)
                    .voteAverage(8.5)
                    .voteCount(5295L)
                    .backdropPath("/hUzeosd33nzE5MCNsZxCGEKTXaQ.png")
                    .popularity(290.05)
                    .tagline("Fica pobre, fica rico")
                    .cast(generateMovieCast(3))
                    .build();

    }

    public static List<Movie> generateMovies(int quantity){
        List<Movie> output = new ArrayList<>();
        for(int i = 1; i < quantity; i++){
            output.add(Movie.builder()
                    .id(Long.valueOf(i))
                    .title("Vingadores Ultimato")
                    .description("Depois do estalar de dedos de Thanos, que dizimou metade da população mundial" +
                            " e destruiu a equipe dos Vingadores, os que sobreviveram têm de tomar uma posição final")
                    .country("U.S.A")
                    .language("English")
                    .releaseyear(2019L)
                    .durationInMinutes(181)
                    .voteAverage(8.5)
                    .voteCount(5295L)
                    .backdropPath("/hUzeosd33nzE5MCNsZxCGEKTXaQ.png")
                    .popularity(290.05)
                    .tagline("O fim de uma era.")
                    .cast(generateMovieCast(3))
                    .build());
        }
        return output;
    }

    public static List<MovieOverviewDTO> generateMovieOverview(int quantity){
        List<MovieOverviewDTO> output = new ArrayList<>();
        for(int i = 1; i < quantity; i++){
            output.add(MovieOverviewDTO.builder()
                    .id(Long.valueOf(i))
                    .title("Vingadores Ultimato")
                    .backdropPath("/hUzeosd33nzE5MCNsZxCGEKTXaQ.png")
                    .build());
        }
        return output;
    }
    public static List<CastMovie> generateMovieCast(int quantity){
        List<CastMovie> output = new ArrayList<>();
        for(int i = 1; i < quantity; i++){
            output.add(CastMovie.builder()
                    .person(Person.builder().build())
                    .movie(Movie.builder().build())
                    .character("Figurante")
                    .build());
        }
        return output;
    }
}
