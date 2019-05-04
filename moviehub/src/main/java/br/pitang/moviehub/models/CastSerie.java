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
@Table(name="mtm_cast_serie")
@AllArgsConstructor
@NoArgsConstructor
public class CastSerie implements Cast{


    @EmbeddedId
    private CastID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("programId")
    private Serie serie;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    private Person person;

    @Column(name = "cas_mtm_caracter")
    private String character;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastSerie cast = (CastSerie) o;
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
        return this.serie;
    }

    @Override
    public String getRole() {
        return this.character;
    }
}
