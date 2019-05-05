package br.pitang.moviehub.models;

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
public class CrewID implements Serializable {

    @Column(name = "cre_program_id")
    private Long programId;

    @Column(name = "cre_person_id")
    private Long personId;

    @Column(name = "cre_cl_job")
    private String job;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewID crewID = (CrewID) o;
        return Objects.equals(programId, crewID.programId) &&
                Objects.equals(personId, crewID.personId) &&
                Objects.equals(job, crewID.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, personId, job);
    }
}
