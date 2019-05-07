package br.pitang.moviehub.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundExceptionDetail {

    private String title;
    private int status;
    private String detail;
    private String developerMessage;
}
