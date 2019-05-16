package br.pitang.moviehub.contracts.models;


import br.pitang.moviehub.models.Person;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.models.embedded.CastId;


public interface Cast {


    CastId id();
    Program getProgram();
    Person getPerson();
    String getCharacter();
}
