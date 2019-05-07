package br.pitang.moviehub.models.embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CastId implements Serializable {

    @Column(name = "cas_program_id")
    private Long programId;

    @Column(name = "cas_person_id")
    private Long personId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastId castId = (CastId) o;
        return Objects.equals(programId, castId.programId) &&
                Objects.equals(personId, castId.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId, personId);
    }
}
