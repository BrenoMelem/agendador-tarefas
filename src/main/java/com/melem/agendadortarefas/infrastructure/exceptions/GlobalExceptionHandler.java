package com.melem.agendadortarefas.infrastructure.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //Implementar tratamento global de exceções para a aplicação, garantindo respostas consistentes e informativas em caso de erros.
    //Como Estamos tratando uma controller, precisamos do ResponseEntity, para retorno do Status HTTP;
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler (UnauthorizedException.class)
    public ResponseEntity<String> handlerUnauthorizedException (UnauthorizedException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.UNAUTHORIZED);
    }
}
