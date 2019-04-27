package br.pitang.moviehub;

import org.hibernate.mapping.Collection;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class DBinitializr {

    public static void main(String args[]){
        RestTemplate restTemplate = new RestTemplate();
        //HashMap pessoa = restTemplate.getForObject("https://api.themoviedb.org/3/person/2?api_key=f0c7808585b35c080abb2d4a6725effe&language=en-US", HashMap.class);

        HashMap movies = restTemplate.getForObject("http://api.themoviedb.org/3/discover/movie?api_key=f0c7808585b35c080abb2d4a6725effe", HashMap.class);

        HashMap object = restTemplate.getForObject("https://api.themoviedb.org/3/movie/550?api_key=f0c7808585b35c080abb2d4a6725effe", HashMap.class);
        System.out.println(object);

        for(Object obj: ((ArrayList)movies.get("results"))){
            System.out.println(obj);
        }


    }
}
