package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name="mtm_cast")
@AllArgsConstructor
@NoArgsConstructor
public class Cast {


    @EmbeddedId
    private CastID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("programId")
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    private Person person;

    @Column(name = "cas_mtm_caracter")
    private String character;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cast cast = (Cast) o;
        return id.equals(cast.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
