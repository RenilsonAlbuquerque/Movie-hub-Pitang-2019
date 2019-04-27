package br.pitang.moviehub.models;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public enum Genre {

    FEMALE(1),MALE(2), OTHERS(3);

    private int value;

    Genre(int value) {
        this.value = value;
    }


}
