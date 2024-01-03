package com.gmail.tvmj.marcosvilchez.SistemaVentas.model;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "ventas")
public class Ventas {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long idVenta;

    @Column
    private long idCliente;
    @Column
    private List<Long> listaIdProductos;
    private static final Logger log = LoggerFactory.getLogger(Ventas.class);
    @Column
    private double total;

    Ventas(){
        this.listaIdProductos = new ArrayList<>();
    }

    public Ventas(long idCliente){
        this();
        this.idCliente = idCliente;
    }

    public Ventas(long idCliente, List<Long> listaIdProductos){this();
        this.idCliente = idCliente;
        this.listaIdProductos = listaIdProductos;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Long> getListaIdProductos() {
        return listaIdProductos;
    }

    public void setListaIdProductos(List<Long> listaIdProductos) {
        this.listaIdProductos = listaIdProductos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ventas ventas)) return false;
        return getIdVenta() == ventas.getIdVenta() && getIdCliente() == ventas.getIdCliente() && Double.compare(getTotal(), ventas.getTotal()) == 0 && Objects.equals(getListaIdProductos(), ventas.getListaIdProductos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVenta(), getIdCliente(), getListaIdProductos(), getTotal());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ventas{ ").append("idVenta= ").append(idVenta).append(", idCliente= ")
                .append(idCliente).append(", listaIdProductos=[ ");
        for(Long idProducto: listaIdProductos){
            sb.append(idProducto).append(", ");
        }
        if (!listaIdProductos.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" ]}");
        return sb.toString();
    }
}
