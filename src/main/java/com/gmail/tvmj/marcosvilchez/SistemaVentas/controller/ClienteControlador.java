package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Clien;
import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.RepoCliente;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClienteControlador {
    private final RepoCliente repo;
    private final EnsambladorCliente model;

    ClienteControlador(RepoCliente repo, EnsambladorCliente model){
        this.repo = repo;
        this.model = model;
    }

    @PostMapping("/clientes")
    ResponseEntity<?> clienteNuevo(@RequestBody Clien clien){
        EntityModel<Clien> newCliente =model.toModel(repo.save(clien));
        return ResponseEntity.created(newCliente.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newCliente);
    }

    @GetMapping("/clientes")
    CollectionModel<EntityModel<Clien>> all(){
        List<EntityModel<Clien>> clientes = repo.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(clientes, linkTo(methodOn(ClienteControlador.class).all()).withSelfRel());
    }

    @GetMapping("/clientes/{idCliente}")
    EntityModel<Clien> one(@PathVariable long idCliente){
        Clien clien = repo.findById(idCliente).orElseThrow(()-> new ClienteExeccion(idCliente));
        return model.toModel(clien);
    }

    @DeleteMapping("/clientes/{idCliente}")
    ResponseEntity<?> borrarCliente(@PathVariable long idCliente){
        repo.deleteById(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clientes/{idCliente}")
    ResponseEntity<?> actualizarCliente(@RequestBody Clien clienOld, @PathVariable long idCliente){
        Clien clienActualizado = repo.findById(idCliente).map(cliente -> {
            cliente.setNombreCompleato(clienOld.getNombreCompleto());
            cliente.setDirreccion(clienOld.getDirreccion());
            return repo.save(cliente);
        }).orElseGet(() -> {
            clienOld.setId(idCliente);
            return repo.save(clienOld);
        });
        EntityModel<Clien> entityModel = model.toModel(clienActualizado);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

}
