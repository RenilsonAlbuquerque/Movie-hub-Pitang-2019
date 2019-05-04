package br.pitang.moviehub.api;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


public abstract class ExternalRequestFactory {

    private static String TMDB_API_KEY = "f0c7808585b35c080abb2d4a6725effe";
    private static short request_counter = 0;
    private static RestTemplate restTemplate = new RestTemplate();

    public static HashMap doRequest(String path) throws InterruptedException {
        if(request_counter == 10){
            Thread.sleep(3000);
            request_counter = 0;
            System.out.println("Waiting");
        }
        request_counter++;
        return restTemplate.getForObject(path, HashMap.class);
    }

    public static String getTmdbApiKey(){
        return TMDB_API_KEY;
    }
}
