package br.pitang.moviehub.models;


import br.pitang.moviehub.models.embedded.CastMovieID;
import br.pitang.moviehub.models.embedded.CastSerieID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Data
@Entity
@Builder
@Table(name="mtm_cast_movie")
@AllArgsConstructor
@NoArgsConstructor
public class CastMovie implements  Serializable {


    @EmbeddedId
    private CastMovieID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    private Person person;

    @Column(name = "cas_mtm_caracter")
    private String character;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastMovie castMovie = (CastMovie) o;
        return Objects.equals(id, castMovie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
