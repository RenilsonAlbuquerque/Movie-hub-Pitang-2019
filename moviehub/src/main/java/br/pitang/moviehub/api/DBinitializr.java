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


    @Autowired
    public DBinitializr(SerieDAO serieDAO, MovieDAO movieDAO){
        this.movieDAO = movieDAO;
        this.serieDAO = serieDAO;
    }

    @Scheduled(fixedDelay = 800000, initialDelay = 1000)
    public void doRequests()throws InterruptedException{
        serieDAO.saveAll(SerieInitializer.listSeries());
        movieDAO.saveAll(MovieInitializer.listMovies());
    }


}
