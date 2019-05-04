package br.pitang.moviehub.api;

import br.pitang.moviehub.models.Genere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class GenereInitializer {

    public static List<Genere> listAllGeneres(ArrayList<HashMap> programGeneres){
        List<Genere> output = new ArrayList<Genere>();
        for (HashMap genre :  programGeneres) {
            output.add(Genere.builder()
                    .id(Long.valueOf(genre.get("id").toString()))
                    .name(genre.get("name").toString())
                    .build());
        }
        return output;
    }
}
