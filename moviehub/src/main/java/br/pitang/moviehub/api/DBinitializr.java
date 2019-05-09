package br.pitang.moviehub.api;



import br.pitang.moviehub.repository.MovieDAO;
import br.pitang.moviehub.repository.SeasonDAO;
import br.pitang.moviehub.repository.SerieDAO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




@Component
@Configuration
@EnableScheduling
public class DBinitializr {


    private SerieDAO serieDAO;
    private MovieDAO movieDAO;
    private MovieInitializer movieInitializer;
    private SerieInitializer serieInitializer;


    @Autowired
    public DBinitializr(SerieDAO serieDAO, MovieDAO movieDAO, MovieInitializer movieInitializer, SerieInitializer serieInitializer){
        this.movieDAO = movieDAO;
        this.serieDAO = serieDAO;
        this.movieInitializer = movieInitializer;
        this.serieInitializer = serieInitializer;
    }

    //@Scheduled(fixedDelay = 800000, initialDelay = 1000)
    public void doRequests()throws InterruptedException{

        //movieDAO.saveAll(movieInitializer.listMovies(movieDAO));
        serieDAO.saveAll(serieInitializer.listSeries(serieDAO));
    }


}
