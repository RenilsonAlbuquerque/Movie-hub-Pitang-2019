package br.pitang.moviehub.models;


import javax.persistence.Entity;
import javax.persistence.Id;


public interface Cast {


    CastID id();
    Program getProgram();
    Person getPerson();
    String getRole();
}
