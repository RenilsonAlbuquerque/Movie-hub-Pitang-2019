package br.pitang.moviehub.handler;

import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.exception.ResourceNotFoundExceptionDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestHandlerexceptionBuilder {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ResourceNotFoundExceptionDetail resourceNotFoundDetail = ResourceNotFoundExceptionDetail.builder()
                .title("Resource not found!")
                .detail(resourceNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .developerMessage(resourceNotFoundException.getClass().getName()).build();

        return new ResponseEntity<>(resourceNotFoundDetail, HttpStatus.NOT_FOUND);
    }
}
