package br.pitang.moviehub.api;


import br.pitang.moviehub.models.*;
import br.pitang.moviehub.repository.CastMovieDAO;
import br.pitang.moviehub.repository.CrewMovieDAO;
import br.pitang.moviehub.repository.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieInitializer {

    @Autowired
    private PersonInitializer personInitializer;

    @Autowired
    private GenereInitializer genereInitializer;

    @Autowired
    private CastMovieDAO castMovieDAO;

    @Autowired
    private CrewMovieDAO crewMovieDAO;

    public List<Movie> listMovies(MovieDAO movieDAO) throws InterruptedException {
        List<Movie> moviesOutput = new ArrayList<>();

        //Request Movies
        for(int paginationIndex = 1; paginationIndex < 2; paginationIndex++){
            List moviesInput = (List) ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/movie/top_rated?api_key="
                    + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br&page=" + paginationIndex).get("results");
            for (HashMap movie : (ArrayList<HashMap>) moviesInput) {

                //Detalhes do filme
                HashMap movieDetail = ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/movie/" + movie.get("id") + "?api_key="
                        + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br");

                CreditMovie creditMovie = personInitializer.castOfMovie(movie.get("id").toString());
                List<GenereMovie> genres = genereInitializer.listMovieGeneres((ArrayList<HashMap>) movieDetail.get("genres"));

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
                        .build();


                movieEntity = movieDAO.save(movieEntity);

                for(CastMovie cast: creditMovie.getCast()){
                    cast.setMovie(movieEntity);
                    castMovieDAO.save(cast);
                }
                for(CrewMovie crew: creditMovie.getCrew()){
                    crew.setMovie(movieEntity);
                    crewMovieDAO.save(crew);
                }
                //movieEntity = movieDAO.findById(movieEntity.getId()).orElse( movieDAO.save(movieEntity) );

                moviesOutput.add(movieEntity);
                System.out.println(movieEntity.getTitle());
            }

        }
        return moviesOutput;
    }

}
