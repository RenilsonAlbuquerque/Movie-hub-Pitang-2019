package br.pitang.moviehub.models;




import lombok.NoArgsConstructor;
import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
public enum Genre {

    FEMALE(1),MALE(2), OTHERS(0);

    private int value;

    Genre(int value) {
        this.value = value;
    }

    public static Genre valueOf(int value) {
        for (Genre  genre : values()) {
            if (genre.value == value) {
                return genre;
            }
        }
        throw new IllegalArgumentException(""+ value);
    }

}
