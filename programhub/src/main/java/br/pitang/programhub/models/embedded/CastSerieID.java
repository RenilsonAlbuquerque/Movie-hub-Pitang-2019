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
public class CastSerieID implements Serializable {

    @Column(name = "cas_serie_id")
    private Long serieId;

    @Column(name = "cas_person_id")
    private Long personId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CastSerieID castID = (CastSerieID) o;
        return Objects.equals(serieId, castID.serieId) &&
                Objects.equals(personId, castID.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serieId, personId);
    }

}
