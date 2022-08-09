package com.sparta.myblog.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RestApiException {
    private String errorMessage;
    private HttpStatus httpStatus;

    public RestApiException(HttpStatus methodNotAllowed, String s) {
        this.errorMessage = s;
        this.httpStatus = methodNotAllowed;
    }
}

