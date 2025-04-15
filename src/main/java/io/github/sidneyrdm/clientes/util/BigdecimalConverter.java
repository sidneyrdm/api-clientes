package io.github.sidneyrdm.clientes.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigdecimalConverter {

    public BigDecimal converter(String value){
        if(value == null){
            return null;
        }

        value = value.replace(".", "");
        value = value.replace(",", ".");

        return new BigDecimal(value);
    }
}
