package com.pure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiRestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String code;
    private final Integer status;
    private final String message;
}
