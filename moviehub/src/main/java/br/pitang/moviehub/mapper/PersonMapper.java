package br.pitang.moviehub.mapper;

import br.pitang.moviehub.dto.PersonDetailDTO;
import br.pitang.moviehub.dto.PersonOverviewDTO;
import br.pitang.moviehub.models.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    public PersonDetailDTO entityToDetail(Person person){
        return new PersonDetailDTO().builder()
                .id(person.getId())
                .name(person.getName())
                .biography(person.getBiography())
                .height(person.getHeight())
                .birthCity(person.getBirthCity())
                .countryWhereLive(person.getCountryWhereLive())
                .genre(person.getGenre())
                .popularity(person.getPopularity())
                .profilePiturePath(person.getProfilePiturePath())
                .build();
    }
    public PersonOverviewDTO entityToOverview(Person person){
        return new PersonOverviewDTO().builder()
                .id(person.getId())
                .name(person.getName())
                .profilePicturePath(person.getProfilePiturePath())
                .build();
    }
}
