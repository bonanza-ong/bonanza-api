package ong.bonanza.beneficiarioapi.adapter.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ong.bonanza.beneficiarioapi.adapter.exception.UnauthorizedException;
import ong.bonanza.beneficiarioapi.domain.exception.RecursoNaoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handle(Throwable e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<String> handle(ObjectOptimisticLockingFailureException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body(String.join(";", e.getAllErrors().stream().map(objectError -> toString(objectError)).toList()));
    }

    private static String toString(ObjectError objectError) {

        if (objectError instanceof FieldError) {
            FieldError fieldError = (FieldError) objectError;
            return String.format("campo=[%s] mensagem=[%s]", fieldError.getField(), fieldError.getDefaultMessage());
        } else {
            return String.format(" mensagem=[%s]", objectError.getDefaultMessage());
        }

    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handle(UnauthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handle(RecursoNaoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
