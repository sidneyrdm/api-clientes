package io.github.sidneyrdm.clientes.rest;

import io.github.sidneyrdm.clientes.exception.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErros(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
       List<String> messages = bindingResult.getAllErrors()
                .stream()
                .map(
                objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

       return new ApiErrors(messages);
    }
}
