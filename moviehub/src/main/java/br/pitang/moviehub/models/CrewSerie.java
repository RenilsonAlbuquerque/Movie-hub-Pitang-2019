package br.pitang.moviehub.models;


import br.pitang.moviehub.models.embedded.CrewId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Builder
@Table(name="mtm_crew_serie")
@AllArgsConstructor
@NoArgsConstructor
public class CrewSerie implements Serializable {

    @Delegate
    @EmbeddedId
    private CrewId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("programId")
    private Serie serie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @MapsId("personId")
    private Person person;

    @Column(name = "cre_mtm_department")
    private String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewSerie crewSerie = (CrewSerie) o;
        return Objects.equals(id, crewSerie.id) &&
                Objects.equals(serie, crewSerie.serie) &&
                Objects.equals(person, crewSerie.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serie, person);
    }
}
