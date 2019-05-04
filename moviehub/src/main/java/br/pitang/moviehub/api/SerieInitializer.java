package br.pitang.moviehub.api;

import br.pitang.moviehub.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public abstract class SerieInitializer {

    public static List<Serie> listSeries() throws InterruptedException {
        List<Serie> seriesOutput = new ArrayList<>();

        //Request Series
        List seriesInput = (List) ExternalRequestFactory
                .doRequest("https://api.themoviedb.org/3/tv/popular?api_key="
                + ExternalRequestFactory.getTmdbApiKey() +  "&language=pt-Br&page=1").get("results");
        for(HashMap serie: (ArrayList<HashMap>) seriesInput){
            //Detalhes do filme
            HashMap serieDetail = ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/tv/"
                    + serie.get("id") + "?api_key="
                    + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br");

            List<CastSerie> castSeries = PersonInitializer.castOfSerie(serie.get("id").toString());
            List<Genere> genres = GenereInitializer.listAllGeneres((ArrayList<HashMap>) serieDetail.get("genres"));

            Serie serieEntity = Serie.builder()
                    .title(serieDetail.get("original_name").toString())
                    .description(serieDetail.get("overview").toString())
                    .country(((List<String>) serieDetail.get("origin_country")).get(0).toString())
                    .language(serieDetail.get("original_language").toString())
                    .releaseyear(Long.valueOf(serieDetail.get("first_air_date").toString().substring(0, 4)))
                    .durationInMinutes(Double.valueOf(((List<Integer>) serieDetail.get("episode_run_time")).get(0).toString()))
                    .generes(genres)
                    .voteAverage(Double.valueOf(serieDetail.get("vote_average").toString()))
                    .voteCount(Long.valueOf(serieDetail.get("vote_count").toString()))
                    .backdropPath(serieDetail.get("backdrop_path").toString())
                    .cast(castSeries)
                    .seasons(retrieveSeasons((List<HashMap>) serieDetail.get("seasons")))
                    .build();
            seriesOutput.add(serieEntity);
            System.out.println(serieEntity.getTitle());
        }
        return seriesOutput;
    }

    private static List<Season> retrieveSeasons(List<HashMap> inputSeasons){
        List<Season> output = new ArrayList<>();
        for(HashMap season: inputSeasons){
            //String date = season.get("air_date").toString();
            Season seasonEntity = Season.builder()
                    .name(season.get("name").toString())
                    .overview(season.get("overview").toString())
                    //.airDate(new Date(date))
                    .episodeCount(Integer.valueOf(season.get("episode_count").toString()))
                    .posterPath(season.get("poster_path").toString())
                    .build();
            output.add(seasonEntity);
        }
        return output;
    }
}
