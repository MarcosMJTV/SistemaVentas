package com.gmail.tvmj.marcosvilchez.SistemaVentas.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdRepo extends JpaRepository<Producto, Long> {
}
