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
@Table(name="mtm_crew_movie")
@AllArgsConstructor
@NoArgsConstructor
public class CrewMovie implements Serializable {

    @Delegate
    @EmbeddedId
    private CrewId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("programId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("personId")
    private Person person;

    @Column(name = "cre_mtm_department")
    private String department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMovie crewMovie = (CrewMovie) o;
        return Objects.equals(id, crewMovie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
