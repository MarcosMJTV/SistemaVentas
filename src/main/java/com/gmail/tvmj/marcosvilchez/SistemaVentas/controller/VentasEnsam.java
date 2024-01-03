package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Ventas;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VentasEnsam implements RepresentationModelAssembler<Ventas, EntityModel<Ventas>> {
    @Override
    public EntityModel<Ventas> toModel(Ventas entity) {
        return EntityModel.of(entity,
                //linkTo(methodOn(VentasControlador.class).one(entity.getIdVenta())).withSelfRel(),
                linkTo(methodOn(VentasControlador.class).all()).withRel("ventas"));
    }
}
