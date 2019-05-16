package br.pitang.moviehub.utils;

import br.pitang.moviehub.models.Genre;
import br.pitang.moviehub.models.Person;
import br.pitang.moviehub.models.Season;
import br.pitang.moviehub.models.Serie;

import java.util.ArrayList;
import java.util.List;

public class PersonGenerator {

    public static List<Person> generatePeople(int quantity){
        List<Person> output = new ArrayList<>();
        for(int i = 1; i < quantity; i++){
            output.add(Person.builder()
                    .id(Long.valueOf(i))
                    .name("Robert")
                    .biography("An excelent actor")
                    .height(174)
                    .birthCity("Oklahoma")
                    .countryWhereLive("U.S.A")
                    .genre(Genre.MALE)
                    .popularity(890D)
                    .profilePiturePath("/asddfrwasda.jpg")
                    .build());
        }
        return output;
    }
}
