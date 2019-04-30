package br.pitang.moviehub.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
public enum Genre {

    FEMALE(1),MALE(2), OTHERS(3);

    private int value;

    Genre(int value) {
        this.value = value;
    }


}
