package br.pitang.moviehub.utils;

import br.pitang.moviehub.dto.SerieOverviewDTO;
import br.pitang.moviehub.models.CastSerie;
import br.pitang.moviehub.models.Person;
import br.pitang.moviehub.models.Season;
import br.pitang.moviehub.models.Serie;

import java.util.ArrayList;
import java.util.List;

public class SeriesGenerator {

    public static List<Serie> generateSeries(int quantity){
        List<Serie> output = new ArrayList<>();
        for(int i = 1; i < quantity; i++){
            output.add(Serie.builder()
                    .id(Long.valueOf(i))
                    .title("Game of Thrones")
                    .description("The Winter is coming")
                    .country("U.S.A")
                    .language("English")
                    .releaseyear(2011L)
                    .durationInMinutes(181)
                    .voteAverage(8.5)
                    .voteCount(5295L)
                    .backdropPath("/hUzeosd33nzE5MCNsZxCGEKTXaQ.png")
                    .popularity(290.05)
                    .cast(generateSerieCast(3))
                    .seasons(new ArrayList<Season>())
                    .build());
        }
        return output;
    }

    public static List<SerieOverviewDTO> generateSerieOverview(int quantity){
        List<SerieOverviewDTO> output = new ArrayList<>();
        for(int i = 1; i < quantity; i++){
            output.add(SerieOverviewDTO.builder()
                    .id(Long.valueOf(i))
                    .title("Vingadores Ultimato")
                    .backdropPath("/hUzeosd33nzE5MCNsZxCGEKTXaQ.png")
                    .build());
        }
        return output;
    }
    public static List<CastSerie> generateSerieCast(int quantity){
        List<CastSerie> output = new ArrayList<>();
        for(int i = 1; i < quantity; i++){
            output.add(CastSerie.builder()
                    .person(Person.builder().build())
                    .serie(Serie.builder().build())
                    .character("Figurante")
                    .build());
        }
        return output;
    }
}
