package org.woof.woofjoybackend.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super("Valor inválido informado. Valor(es) aceito(s): [%s]".formatted(message));
    }
}
