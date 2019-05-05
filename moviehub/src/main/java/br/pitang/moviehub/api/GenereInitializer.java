package br.pitang.moviehub.api;

import br.pitang.moviehub.models.GenereMovie;
import br.pitang.moviehub.models.GenereSerie;
import br.pitang.moviehub.repository.GenereMovieDAO;
import br.pitang.moviehub.repository.GenereSerieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public  class GenereInitializer {

    @Autowired
    private GenereSerieDAO serieDAO;

    @Autowired
    private GenereMovieDAO movieDAO;

    public  List<GenereSerie> listSerieGeneres(ArrayList<HashMap> programGeneres){
        List<GenereSerie> output = new ArrayList<GenereSerie>();
        for (HashMap genreHash :  programGeneres) {
            GenereSerie genere = GenereSerie.builder()
                    .id(Long.valueOf(genreHash.get("id").toString()))
                    .name(genreHash.get("name").toString())
                    .build();
            genere = serieDAO.findById(genere.getId())
                    .orElse( serieDAO.save(genere) );
            output.add(genere);
        }
        return output;
    }
    public List<GenereMovie> listMovieGeneres(ArrayList<HashMap> programGeneres){

        List<GenereMovie> output = new ArrayList<GenereMovie>();
        for (HashMap genreHash :  programGeneres) {
             GenereMovie genere = GenereMovie.builder()
                    .id(Long.valueOf(genreHash.get("id").toString()))
                    .name(genreHash.get("name").toString())
                    .build();
            genere = movieDAO.findById(genere.getId())
                    .orElse( movieDAO.save(genere) );
            output.add(genere);
        }
        return output;
    }
}
