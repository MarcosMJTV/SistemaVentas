package com.gmail.tvmj.marcosvilchez.SistemaVentas.controller;

import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.Producto;
import com.gmail.tvmj.marcosvilchez.SistemaVentas.model.ProdRepo;
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
public class ProductoControlador {

    private final ProdRepo repo;
    private final ProdEnsamblador model;


    ProductoControlador(ProdRepo repo, ProdEnsamblador model){
        this.repo = repo;
        this.model = model;
    }

    @PostMapping("/productos")
    ResponseEntity<?> productoNuevo(@RequestBody Producto producto){
        EntityModel<Producto> newProducto =model.toModel(repo.save(producto));
        return ResponseEntity.created(newProducto.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newProducto);
    }

    @GetMapping("/productos")
    CollectionModel<EntityModel<Producto>> all(){
        List<EntityModel<Producto>> productos = repo.findAll().stream().map(model::toModel).collect(Collectors.toList());
        return CollectionModel.of(productos, linkTo(methodOn(ProductoControlador.class).all()).withSelfRel());
    }

    @GetMapping("/productos/{idProducto}")
   EntityModel<Producto> one(@PathVariable long idProducto){
        Producto producto = repo.findById(idProducto).orElseThrow(() -> new ExeccionProducto(idProducto));
        return model.toModel(producto);
    }

    @PutMapping("/productos/{idProducto}")
    ResponseEntity<?> actualizarProducto(@RequestBody Producto productoOld, @PathVariable long idProducto){
        Producto productoActualizado = repo.findById(idProducto).map(producto -> {
            producto.setNombreProducto(productoOld.getNombreProducto());
            producto.setDescripcion(productoOld.getDescripcion());
            producto.setPrecio(productoOld.getPrecio());
            return repo.save(producto);
        }).orElseGet(() -> {
            productoOld.setIdProducto(idProducto);
            return repo.save(productoOld);
        });
        EntityModel<Producto> entityModel = model.toModel(productoActualizado);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/productos/{idProducto}")
    ResponseEntity<?> borrarProducto(@PathVariable long idProducto){
        repo.deleteById(idProducto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productos/precio/{idProducto}")
    double precioProducto(@PathVariable Long idProducto){
        Producto producto = repo.findById(idProducto).orElseThrow(() -> new ExeccionProducto(idProducto));
        return producto.getPrecio();
    }
}
