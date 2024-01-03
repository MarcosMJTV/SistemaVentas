package com.gmail.tvmj.marcosvilchez.SistemaVentas.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(schema = "producto")
public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;
    @Column
    private String nombreProducto;
    @Column
    private String descripcion;
    @Column
    private double precio;


    Producto(){}

    public Producto(String nombreProducto, String descripcion, long precio){
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return getIdProducto() == producto.getIdProducto() && getPrecio() == producto.getPrecio() && Objects.equals(getNombreProducto(), producto.getNombreProducto()) && Objects.equals(getDescripcion(), producto.getDescripcion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProducto(), getNombreProducto(), getDescripcion(), getPrecio());
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
