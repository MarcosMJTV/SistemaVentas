package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner iniciarBaseDatos(RepoCliente cliRepo, ProdRepo Produrepo, RepoVent venRepo){
        return args -> {
            Clien c1= cliRepo.save(new Clien("Marcos Vilchez", "las plasmeras"));
            Clien c2= cliRepo.save(new Clien("Daniel", "Arrieta", "Caballito"));
            cliRepo.findAll().forEach(cliente -> log.info("Cargando cliente: " + cliente));

            Producto p1 = Produrepo.save(new Producto("cepillo", "un cepillo para peinar el cabello", 4000));
            Producto p2 = Produrepo.save(new Producto("cartera", "cartera masculina, use personal", 7000));
            Produrepo.findAll().forEach(Producto -> log.info("Cargando producto: " + Producto));

           Ventas v1 = new Ventas(c1.getId());
           v1.getListaIdProductos().add(p1.getIdProducto());
           v1.getListaIdProductos().add(p2.getIdProducto());
           venRepo.save(v1);

            Ventas v2 = new Ventas(c2.getId());
            v2.getListaIdProductos().add(p1.getIdProducto());
            v2.getListaIdProductos().add(p2.getIdProducto());
            venRepo.save(v2);

            venRepo.findAll().forEach(ventas -> log.info("Cargando venta: " + ventas));

        };
    }
}
