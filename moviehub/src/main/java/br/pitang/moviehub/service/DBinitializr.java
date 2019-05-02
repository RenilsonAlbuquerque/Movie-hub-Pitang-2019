package br.pitang.moviehub.service;



import br.pitang.moviehub.models.*;
import br.pitang.moviehub.repository.MovieDAO;
import br.pitang.moviehub.repository.SeasonDAO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@Component
@Configuration
@EnableScheduling
public class DBinitializr {


    private SeasonDAO seasonDAO;
    private RestTemplate restTemplate;
    private MovieDAO movieDAO;
    private String TMDB_API_KEY = "f0c7808585b35c080abb2d4a6725effe";

    @Autowired
    public DBinitializr(SeasonDAO seasonDAO, MovieDAO movieDAO){
        this.movieDAO = movieDAO;
        this.seasonDAO = seasonDAO;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedDelay = 800000, initialDelay = 1000)
    public void doRequests() throws Exception{
        //Primeiros filmes
        List movies = (List) restTemplate.getForObject("https://api.themoviedb.org/3/movie/top_rated?api_key="
                + this.TMDB_API_KEY +  "&language=pt-Br&page=1", HashMap.class).get("results");
        for(HashMap movie: (ArrayList<HashMap>) movies){

            //Detalhes do filme
            HashMap movieDetail = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movie.get("id") + "?api_key="
                    + this.TMDB_API_KEY + "&language=pt-Br", HashMap.class);

            List<CastMovie> castMovie =  castOfMovie(movie.get("id").toString());
            List<Genere> genres = new ArrayList<Genere>();
            for(HashMap genre: (ArrayList<HashMap>) movieDetail.get("genres")){
                genres.add( Genere.builder()
                        .id( Long.valueOf(genre.get("id").toString()))
                        .name(genre.get("name").toString())
                        .build());
            }
            Movie movieEntity =  Movie.builder()
                    .title(movieDetail.get("title").toString())
                    .description(movieDetail.get("overview").toString())
                    .country(((List<HashMap>)movieDetail.get("production_countries")).get(0).get("name").toString())
                    .language(((List<HashMap>)movieDetail.get("spoken_languages")).get(0).get("name").toString())
                    .releaseyear(Long.valueOf(movieDetail.get("release_date").toString().substring(0,4)))
                    .durationInMinutes(Double.valueOf(movieDetail.get("runtime").toString()))
                    .generes(genres)
                    .voteAverage(Double.valueOf(movieDetail.get("vote_average").toString()))
                    .voteCount(Long.valueOf(movieDetail.get("vote_count").toString()))
                    .backdropPath(movieDetail.get("poster_path").toString())
                    .tagline(movieDetail.get("tagline").toString())
                    .cast(castMovie)
                    .build();
            System.out.println(movieEntity);
        }

    }

    private ArrayList<CastMovie> castOfMovie(String movieId) throws Exception{
        ArrayList<CastMovie> output = new ArrayList<>();
        List<HashMap> creditisRequest = (List) restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+ movieId + "/credits?api_key="
                + this.TMDB_API_KEY + "&language=pt-Br", HashMap.class).get("cast");
        for(HashMap credit: creditisRequest){
            HashMap personDetail = restTemplate.getForObject("https://api.themoviedb.org/3/person/"
                    + credit.get("id") + "?api_key="
                    + this.TMDB_API_KEY + "&language=pt-Br", HashMap.class);
            Thread.sleep(1000);
            String birthPlaceString = personDetail.get("place_of_birth").toString();

            Person personEntity = Person.builder()
                    .name(personDetail.get("name").toString())
                    .biography(personDetail.get("biography").toString())
                    .height(1)
                    .birthCity(birthPlaceString.substring(0,birthPlaceString.lastIndexOf(',')))
                    .countryWhereLive(birthPlaceString.substring(birthPlaceString.lastIndexOf(","),
                            birthPlaceString.length()))
                    .genre(Genre.valueOf((int)personDetail.get("gender")))
                    .profilePiturePath(personDetail.get("profile_path").toString())
                    .build();

            output.add(
                    CastMovie.builder()
                            .person(personEntity)
                            .character(credit.get("character").toString())
                    .build());
        }
        return output;
    }


}
