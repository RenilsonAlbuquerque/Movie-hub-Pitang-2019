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
public class CrewMovieID implements Serializable {

    @Column(name = "cre_movie_id")
    private Long movieId;

    @Column(name = "cre_person_id")
    private Long personId;

    @Column(name = "cre_cl_job")
    private String job;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMovieID crewID = (CrewMovieID) o;
        return Objects.equals(movieId,  crewID.movieId) &&
                Objects.equals(personId,  crewID.movieId) &&
                Objects.equals(job, crewID.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, personId, job);
    }
}
