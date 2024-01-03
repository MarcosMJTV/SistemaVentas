package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

public class ClienteExeccion extends RuntimeException {

    ClienteExeccion(Long id){
        super("cliente con id: " + id + " no encontrado");
    }
}
