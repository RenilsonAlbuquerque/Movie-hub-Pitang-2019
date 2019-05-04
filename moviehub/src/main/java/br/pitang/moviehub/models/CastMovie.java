package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Builder
@Table(name="mtm_cast_movie")
@AllArgsConstructor
@NoArgsConstructor
public class CastMovie implements Cast {


    @EmbeddedId
    private CastID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("programId")
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
        CastMovie cast = (CastMovie) o;
        return id.equals(cast.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
