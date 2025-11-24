package com.pilitejeamigurumis.restcontrollers.dto.carrito;

import com.pilitejeamigurumis.entities.CarritoItem;

public class CarritoItemDto {

  private Long id;
  private Long productoId;
  private String nombreProducto;
  private Integer cantidad;
  private Integer precioUnitario;

  public static CarritoItemDto fromEntity(CarritoItem ci) {
    CarritoItemDto dto = new CarritoItemDto();
    dto.id = ci.getId();
    dto.productoId = ci.getProducto().getId();
    dto.nombreProducto = ci.getProducto().getNombre();
    dto.cantidad = ci.getCantidad();
    dto.precioUnitario = ci.getPrecioUnit();
    return dto;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Long getProductoId() { return productoId; }
  public void setProductoId(Long productoId) { this.productoId = productoId; }

  public String getNombreProducto() { return nombreProducto; }
  public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

  public Integer getCantidad() { return cantidad; }
  public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

  public Integer getPrecioUnitario() { return precioUnitario; }
  public void setPrecioUnitario(Integer precioUnitario) { this.precioUnitario = precioUnitario; }
}
