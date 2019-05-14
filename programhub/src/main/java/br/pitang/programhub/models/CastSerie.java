package br.pitang.moviehub.models;


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
@Table(name="mtm_cast_serie")
@AllArgsConstructor
@NoArgsConstructor
public class CastSerie implements  Serializable {


    @EmbeddedId
    private CastSerieID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("serieId")
    private Serie serie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("personId")
    private Person person;

    @Column(name = "cas_mtm_caracter")
    private String character;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastSerie castSerie = (CastSerie) o;
        return Objects.equals(id, castSerie.id) &&
                Objects.equals(serie, castSerie.serie) &&
                Objects.equals(person, castSerie.person) &&
                Objects.equals(character, castSerie.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serie, person, character);
    }

}
