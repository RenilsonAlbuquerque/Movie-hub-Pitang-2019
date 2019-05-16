package br.pitang.moviehub.mapper;

import br.pitang.moviehub.dto.CastDTO;
import br.pitang.moviehub.models.Cast;
import org.springframework.stereotype.Service;

@Service
public class CastMapper {

    public CastDTO entityToOverview(Cast cast){
        return CastDTO.builder()
                .character(cast.getCharacter())
                .actor(cast.getPerson().getName())
                .profilePicture(cast.getPerson().getProfilePiturePath())
                .build();
    }
}
