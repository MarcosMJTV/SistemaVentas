package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ProdEnsamblador implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {


    @Override
    public EntityModel<Producto> toModel(Producto entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ProductoControlador.class).one(entity.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProductoControlador.class).all()).withRel("productos"));
    }
}
