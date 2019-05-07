package br.pitang.moviehub.models;


import br.pitang.moviehub.models.embedded.CastSerieID;


public interface Cast {


    CastSerieID id();
    Program getProgram();
    Person getPerson();
    String getRole();
}
