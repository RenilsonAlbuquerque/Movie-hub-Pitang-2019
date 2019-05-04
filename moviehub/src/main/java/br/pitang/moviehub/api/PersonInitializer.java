package br.pitang.moviehub.api;

import br.pitang.moviehub.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class PersonInitializer {

    public static ArrayList<CastMovie> castOfMovie(String movieId)  throws InterruptedException {
        ArrayList<CastMovie> output = new ArrayList<>();
        List<HashMap> creditisRequest = (List) ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/movie/" + movieId + "/credits?api_key="
                + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br").get("cast");

        for (HashMap credit : creditisRequest) {
            CastMovie.builder()
                    .person(buildPerson(credit))
                    .character(credit.get("character").toString())
                    .build();
        }

        return output;
    }

    public static ArrayList<CastSerie> castOfSerie(String serieId)  throws InterruptedException {
        ArrayList<CastSerie> output = new ArrayList<>();
        List<HashMap> creditisRequest = (List) ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/tv/" + serieId + "/credits?api_key="
                + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br").get("cast");

        for (HashMap credit : creditisRequest) {
            output.add(
                    CastSerie.builder()
                            .person(buildPerson(credit))
                            .character(credit.get("character").toString())
                            .build());
        }

        return output;
    }
    private static Person buildPerson(HashMap credit) throws InterruptedException{
        HashMap personDetail = ExternalRequestFactory.doRequest("https://api.themoviedb.org/3/person/"
                + credit.get("id") + "?api_key="
                + ExternalRequestFactory.getTmdbApiKey() + "&language=pt-Br");

        System.out.println(personDetail);
        String birthCityString = retrieveBirthCity(personDetail.get("place_of_birth"));
        String countryString = retrieveCountry(personDetail.get("place_of_birth"));

        Person personEntity = Person.builder()
                .name(personDetail.get("name").toString())
                .biography(personDetail.get("biography").toString())
                .height(1)
                .birthCity(birthCityString)
                .countryWhereLive(countryString)
                .genre(Genre.valueOf((int) personDetail.get("gender")))
                .profilePiturePath((personDetail.get("profile_path")) != null ? personDetail.get("profile_path").toString() : "")
                .build();

        return personEntity;
    }

    private static String retrieveBirthCity(Object inputFromApi){
        String output = "";
        if(inputFromApi != null){
            char rejex;
            if(inputFromApi.toString().contains(",")){
                rejex = ',';
            }else if(inputFromApi.toString().contains("-")){
                rejex = '-';
            }else{
                if(inputFromApi.toString().trim().length() > 0){
                    return inputFromApi.toString();
                }else{
                    return "";
                }
            }
            output = inputFromApi.toString().substring(0,inputFromApi.toString().lastIndexOf(rejex));
        }
        return output;
    }
    private static String retrieveCountry(Object inputFromApi){
        String output = "";
        if(inputFromApi != null){
            char rejex = findRegex(inputFromApi.toString());
            if(rejex == 0){
                return inputFromApi.toString();
            }
            output = inputFromApi.toString().substring(inputFromApi.toString().lastIndexOf(rejex),
                    inputFromApi.toString().length());
        }
        return output;
    }
    private static char findRegex(String input){
        if(input.toString().contains(",")){
            return  ',';
        }else if(input.toString().contains("-")){
            return  '-';
        }else{
            return 0;
        }
    }
}
