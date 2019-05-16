package br.pitang.moviehub.mapper;

import br.pitang.moviehub.dto.CastDTO;
import br.pitang.moviehub.contracts.models.Cast;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CastMapper {

    public CastDTO entityToOverview(Cast cast){
        return CastDTO.builder()
                .character(cast.getCharacter())
                .actor(cast.getPerson().getName())
                .profilePicture(cast.getPerson().getProfilePiturePath())
                .build();
    }
    public List<CastDTO> entityToOverview(List<Cast> cast){
        return cast.stream().map( entity -> CastDTO.builder()
                .character(entity.getCharacter())
                .actor(entity.getPerson().getName())
                .profilePicture(entity.getPerson().getProfilePiturePath())
                .build()).collect(Collectors.toList());
    }
}
