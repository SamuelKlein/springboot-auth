package dev.samuk.auth.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //other exception handlers

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> errorConstraintViolationException(ConstraintViolationException ex) {
        String mess = null;
        for(ConstraintViolation c : ex.getConstraintViolations()) {
            mess = c.getMessage();
        };
        return ResponseEntity.badRequest().body(new ErrorRest(mess));
    }

    private class ErrorRest {

        public ErrorRest(String mensagem) {
            this.mensagem = mensagem;
        }

        private String mensagem;

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }
}