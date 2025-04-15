package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor

public class ResponseBase<T> {
    private int code;
    private String message;
    private Optional<T> data;
}
