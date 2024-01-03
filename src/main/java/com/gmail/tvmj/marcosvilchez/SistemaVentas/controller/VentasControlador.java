package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Clien;
import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Producto;
import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Ventas;
import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.RepoVent;
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
public class VentasControlador {
    private final RepoVent repo;
    private final VentasEnsam model;
    private final ProductoControlador pc;


    VentasControlador(RepoVent repo, VentasEnsam model, ProductoControlador pc){
        this.repo = repo;
        this.model = model;
        this.pc = pc;
    }

   @GetMapping("/ventas")
   CollectionModel<EntityModel<Ventas>> all(){
       List<EntityModel<Ventas>> venta = repo.findAll().stream().map(model::toModel).collect(Collectors.toList());
       return CollectionModel.of(venta, linkTo(methodOn(VentasControlador.class).all()).withSelfRel());
   }

    @GetMapping("/ventas/{idVentas}")
    EntityModel<Ventas> one(@PathVariable long idVentas){
        Ventas ventas = repo.findById(idVentas).orElseThrow(() -> new VentExeccion(idVentas));
        ventas.setTotal(sumarPrecios(ventas));
        return model.toModel(ventas);
    }

    @PostMapping("/ventas")
    ResponseEntity<?> ventaNuevo(@RequestBody Ventas venta){
        venta.setTotal(sumarPrecios(venta));
        EntityModel<Ventas> newVenta = model.toModel(repo.save(venta));
        return ResponseEntity.created(newVenta.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newVenta);
    }

    @DeleteMapping("/ventas/{idVentas}")
    ResponseEntity<?> borrarVenta(@PathVariable long idVentas){
        repo.deleteById(idVentas);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ventas/{idVentas}")
    ResponseEntity<?> actualizarVenta(@RequestBody Ventas ventaOld, @PathVariable long idVentas){
        Ventas ventaActualizado = repo.findById(idVentas).map(venta -> {
            venta.setIdCliente(ventaOld.getIdCliente());
            venta.setListaIdProductos(venta.getListaIdProductos());
            venta.setTotal(sumarPrecios(ventaOld));
            return repo.save(venta);
        }).orElseGet(() -> {
            ventaOld.setIdVenta(idVentas);
            return repo.save(ventaOld);
        });
        EntityModel<Ventas> entityModel = model.toModel(ventaActualizado);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    double sumarPrecios(Ventas ventas){
        double total =0;
        for(long producto: ventas.getListaIdProductos()){
            pc.one(producto);
            total += pc.precioProducto(producto);
        }
        return total;
    }
}
