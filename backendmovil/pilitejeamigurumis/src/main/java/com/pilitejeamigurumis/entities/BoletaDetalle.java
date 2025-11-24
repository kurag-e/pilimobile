package com.pilitejeamigurumis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "boleta_detalle")
public class BoletaDetalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "boleta_id")
  private Boleta boleta;

  @ManyToOne
  @JoinColumn(name = "producto_id")
  private Producto producto;

  private Integer cantidad;

  @Column(name = "precio_unit")
  private Integer precioUnit;

  @Column(name = "total_linea")
  private Integer totalLinea;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Boleta getBoleta() { return boleta; }
  public void setBoleta(Boleta boleta) { this.boleta = boleta; }

  public Producto getProducto() { return producto; }
  public void setProducto(Producto producto) { this.producto = producto; }

  public Integer getCantidad() { return cantidad; }
  public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

  public Integer getPrecioUnit() { return precioUnit; }
  public void setPrecioUnit(Integer precioUnit) { this.precioUnit = precioUnit; }

  public Integer getTotalLinea() { return totalLinea; }
  public void setTotalLinea(Integer totalLinea) { this.totalLinea = totalLinea; }
}
