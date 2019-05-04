package br.pitang.moviehub.api;

public enum ProgramTypeEnum {
    
    MOVIE(1), SERIE(2);

    private int value;

    ProgramTypeEnum(int value) {
        this.value = value;
    }
}
