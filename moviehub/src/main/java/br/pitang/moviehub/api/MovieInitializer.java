package br.pitang.moviehub.api;


import br.pitang.moviehub.models.CastMovie;
import br.pitang.moviehub.models.Genere;
import br.pitang.moviehub.models.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class MovieInitializer {

    public static List<Movie> listMovies() throws InterruptedException {
        List<Movie> moviesOutput = new ArrayList<>();

        //Request Movies
        List moviesInput = (List) ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/movie/top_rated?api_key="
                + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br&page=1").get("results");
        for (HashMap movie : (ArrayList<HashMap>) moviesInput) {

            //Detalhes do filme
            HashMap movieDetail = ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/movie/" + movie.get("id") + "?api_key="
                    + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br");

            CreditMovie creditMovie = PersonInitializer.castOfMovie(movie.get("id").toString());
            List<Genere> genres = GenereInitializer.listAllGeneres((ArrayList<HashMap>) movieDetail.get("genres"));

            Movie movieEntity = Movie.builder()
                    .title(movieDetail.get("title").toString())
                    .description(movieDetail.get("overview").toString())
                    .country(((List<HashMap>) movieDetail.get("production_countries")).get(0).get("name").toString())
                    .language(((List<HashMap>) movieDetail.get("spoken_languages")).get(0).get("name").toString())
                    .releaseyear(Long.valueOf(movieDetail.get("release_date").toString().substring(0, 4)))
                    .durationInMinutes(Double.valueOf(movieDetail.get("runtime").toString()))
                    .generes(genres)
                    .voteAverage(Double.valueOf(movieDetail.get("vote_average").toString()))
                    .voteCount(Long.valueOf(movieDetail.get("vote_count").toString()))
                    .backdropPath(movieDetail.get("poster_path").toString())
                    .tagline(movieDetail.get("tagline").toString())
                    .cast(creditMovie.getCast())
                    .crew(creditMovie.getCrew())
                    .build();
            moviesOutput.add(movieEntity);
            System.out.println(movieEntity.getTitle());

        }
        return moviesOutput;
    }

}
