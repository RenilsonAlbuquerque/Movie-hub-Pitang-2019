package br.pitang.moviehub.api;


import br.pitang.moviehub.models.CastMovie;
import br.pitang.moviehub.models.CrewMovie;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreditMovie {

    private List<CastMovie> cast;
    private List<CrewMovie> crew;
}
