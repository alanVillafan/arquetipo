package com.arquetipo.inicial.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MsException extends Exception {
    private String codigo;


    public MsException(String error, String codigo) {
        super(error);
        this.codigo = codigo;
    }

    MsException(String error) {
        super(error);
    }

}
