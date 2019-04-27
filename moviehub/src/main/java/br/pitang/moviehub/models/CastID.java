package br.pitang.moviehub.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@AllArgsConstructor
public class CastID implements Serializable {

    @Column(name = "cas_program_id")
    private Long programId;

    @Column(name = "cas_person_id")
    private Long personId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastID castID = (CastID) o;
        return Objects.equals(programId, castID.programId) &&
                Objects.equals(personId, castID.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, personId);
    }
}
