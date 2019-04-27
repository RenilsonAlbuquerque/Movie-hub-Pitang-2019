package br.pitang.moviehub.models;



import javax.persistence.Embeddable;


@Embeddable
public enum Genre {

    FEMALE(1),MALE(2), OTHERS(3);

    private int value;

    Genre(int value) {
        this.value = value;
    }


}
