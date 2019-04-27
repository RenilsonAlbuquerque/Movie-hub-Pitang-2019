package br.pitang.moviehub;



import br.pitang.moviehub.repository.SeasonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;

//@Component
public class DBinitializr
        //implements ApplicationRunner
        {
/*
    @PersistenceContext
    private EntityManager entityManager;
    private SeasonDAO seasonDAO;
    private RestTemplate restTemplate;
    private String TMDB_API_KEY = "f0c7808585b35c080abb2d4a6725effe";

    @Autowired
    public DBinitializr(SeasonDAO seasonDAO){
        this.seasonDAO = seasonDAO;
        this.restTemplate = new RestTemplate();
    }

    public void run(ApplicationArguments arguments){
        System.out.println("Here we comes");
        doRequest("https://api.themoviedb.org/3/movie/top_rated", "&language=en-PT&page=1");
    }

    /*
    public static void main(String args[]){
        //HashMap pessoa = restTemplate.getForObject("https://api.themoviedb.org/3/person/2?api_key=f0c7808585b35c080abb2d4a6725effe&language=en-US", HashMap.class);


        HashMap movies = doRequest("http://api.themoviedb.org/3/discover/movie");

        HashMap object = doRequest("https://api.themoviedb.org/3/movie/550");
        System.out.println(object);

        for(Object obj: ((ArrayList)movies.get("results"))){
            System.out.println(obj);
        }


    }


    private HashMap doRequest(String path, String... args){
        return  restTemplate.getForObject(path .concat("?api_key=").concat(TMDB_API_KEY ) + args, HashMap.class);
    }
    */

}
