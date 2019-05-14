package br.pitang.moviehub.models.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CrewSerieID implements Serializable {

    @Column(name = "cre_serie_id")
    private Long serieId;

    @Column(name = "cre_person_id")
    private Long personId;

    @Column(name = "cre_cl_job")
    private String job;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewSerieID crewID = (CrewSerieID) o;
        return Objects.equals(serieId, crewID.serieId) &&
                Objects.equals(personId, crewID.personId) &&
                Objects.equals(job, crewID.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serieId, personId, job);
    }
}
