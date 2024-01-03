package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

public class VentExeccion extends RuntimeException{

    VentExeccion(long id){
        super("Venta con id: " + id + " no encontrado");
    }
}
