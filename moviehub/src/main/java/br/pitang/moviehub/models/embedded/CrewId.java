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
public class CrewId implements Serializable {

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
        CrewId crewID = (CrewId) o;
        return Objects.equals(programId,  crewID.programId) &&
                Objects.equals(personId,  crewID.programId) &&
                Objects.equals(job, crewID.job);
    }
}
