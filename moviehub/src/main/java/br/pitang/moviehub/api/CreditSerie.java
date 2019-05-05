package br.pitang.moviehub.api;

import br.pitang.moviehub.models.CastSerie;
import br.pitang.moviehub.models.CrewSerie;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreditSerie {
    private List<CastSerie> cast;
    private List<CrewSerie> crew;
}
