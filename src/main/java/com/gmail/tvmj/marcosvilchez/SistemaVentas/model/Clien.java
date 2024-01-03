package com.gmail.tvmj.marcosvilchez.SistemaVentas.model;



import jakarta.persistence.*;


import java.util.Objects;

@Entity
@Table(schema = "cliente")
public class Clien {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String dirreccion;


    Clien(){}

    public Clien(String nombre, String apellido, String dirreccion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dirreccion = dirreccion;
    }

    public Clien(String nombreCompleto, String dirreccion){
        String[] partes = nombreCompleto.split(" ");
        this.nombre = partes[0];
        this.apellido = partes[1];
        this.dirreccion = dirreccion;
    }

    public String getNombreCompleto(){
        return this.nombre + " " + this.apellido;
    }
    public void setNombreCompleato(String nombreCompleto){
        String[] partes =nombreCompleto.split(" ");
        this.nombre =partes[0];
        this.apellido = partes[1];
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDirreccion() {
        return this.dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clien clien)) return false;
        return getId() == clien.getId() && Objects.equals(getNombre(), clien.getNombre()) && Objects.equals(getApellido(), clien.getApellido()) && Objects.equals(getDirreccion(), clien.getDirreccion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellido(), getDirreccion());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dirreccion='" + dirreccion + '\'' +
                '}';
    }
}
