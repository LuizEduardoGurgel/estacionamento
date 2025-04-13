package br.com.luiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedLicensePlateException extends RuntimeException {
    public DuplicatedLicensePlateException(String message) {
        super(message);
    }
}
