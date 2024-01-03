package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AvisoVent {
    @ResponseBody
    @ExceptionHandler(VentExeccion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String VentasNoFound(VentExeccion ex){
        return ex.getMessage();
    }
}
