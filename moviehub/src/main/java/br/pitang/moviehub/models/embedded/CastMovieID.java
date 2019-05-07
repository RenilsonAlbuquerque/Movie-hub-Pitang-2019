package br.pitang.moviehub.models.embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CastMovieID implements Serializable {

    @Column(name = "cas_movie_id")
    private Long movieId;

    @Column(name = "cas_person_id")
    private Long personId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastMovieID castID = (CastMovieID) o;
        return Objects.equals(movieId, castID.movieId) &&
                Objects.equals(personId, castID.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, personId);
    }

}
