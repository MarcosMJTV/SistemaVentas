package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

public class ExeccionProducto extends RuntimeException{

    ExeccionProducto(long id){
        super("Producto con id: " + id + " no encontrado");
    }
}
