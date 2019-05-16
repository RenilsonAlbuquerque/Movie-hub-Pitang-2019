package br.pitang.moviehub.models;


import br.pitang.moviehub.models.embedded.CastId;


public interface Cast {


    CastId id();
    Program getProgram();
    Person getPerson();
    String getCharacter();
}
