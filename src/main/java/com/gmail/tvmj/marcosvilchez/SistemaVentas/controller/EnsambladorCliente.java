package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Clien;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class EnsambladorCliente implements RepresentationModelAssembler<Clien, EntityModel<Clien>> {
    @Override
    public EntityModel<Clien> toModel(Clien entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ClienteControlador.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(ClienteControlador.class).all()).withRel("clientes"));
    }
}
