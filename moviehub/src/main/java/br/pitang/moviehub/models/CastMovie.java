package br.pitang.moviehub.models;


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
public class CastMovie implements Cast, Serializable {


    @EmbeddedId
    private CastID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("programId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @MapsId("personId")
    private Person person;

    @Column(name = "cas_mtm_caracter")
    private String character;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastMovie castMovie = (CastMovie) o;
        return Objects.equals(id, castMovie.id) &&
                Objects.equals(movie, castMovie.movie) &&
                Objects.equals(person, castMovie.person) &&
                Objects.equals(character, castMovie.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, person, character);
    }

    @Override
    public CastID id() {
        return this.id;
    }

    @Override
    public Program getProgram() {
        return this.movie;
    }

    @Override
    public String getRole() {
        return this.getCharacter();
    }
}
