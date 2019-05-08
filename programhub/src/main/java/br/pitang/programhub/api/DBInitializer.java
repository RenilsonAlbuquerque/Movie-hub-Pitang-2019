package br.pitang.programhub.api;

import br.pitang.programhub.model.Season;
import br.pitang.programhub.model.Serie;
import br.pitang.programhub.repository.SerieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class DBInitializer {

    @Autowired
    private SerieDAO serieDAO;

    @Scheduled(fixedDelay = 800000, initialDelay = 1000)
    public void doRequests()throws InterruptedException{
        List<Season> seasons = new ArrayList<Season>();
        Season season = new Season();
        season.setName("Primeira");
        season.setOverview("Ned stark morre");
        season.setPosterPath("/8u9w8h398aoisdjas");
        seasons.add(season);

        Serie serie = new Serie();
        serie.setName("Game of Thrones");
        serie.setSeasons(seasons);
        serieDAO.save(serie);
    }
}
