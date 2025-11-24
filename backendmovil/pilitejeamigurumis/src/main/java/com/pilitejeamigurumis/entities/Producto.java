package com.pilitejeamigurumis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Producto {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(length = 255)
  private String descripcion;

  @Column(nullable = false)
  private Integer precio;

  @Column(nullable = false)
  private Integer stock;

  @Column(name = "stock_critico", nullable = false)
  private Integer stockCritico = 0;

  private String imagen;

  @Column(nullable = false, length = 20)
  private String estado;

  @Column(name = "fecha_creacion", length = 20)
  private String fechaCreacion;

  @ManyToOne
  @JoinColumn(name = "categoria_id", nullable = true)
  private Categoria categoria;

  // derivado solo para lectura en json (no persistido)
  @Transient
  public String getEstadoStock() {
    int crit = stockCritico == null ? 0 : stockCritico;
    int stk  = stock == null ? 0 : stock;
    return (stk <= crit) ? "CRITICO" : "OK";
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getDescripcion() { return descripcion; }
  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

  public Integer getPrecio() { return precio; }
  public void setPrecio(Integer precio) { this.precio = precio; }

  public Integer getStock() { return stock; }
  public void setStock(Integer stock) { this.stock = stock; }

  public Integer getStockCritico() { return stockCritico; }
  public void setStockCritico(Integer stockCritico) { this.stockCritico = stockCritico; }

  public String getImagen() { return imagen; }
  public void setImagen(String imagen) { this.imagen = imagen; }

  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; }

  public String getFechaCreacion() { return fechaCreacion; }
  public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

  public Categoria getCategoria() { return categoria; }
  public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
