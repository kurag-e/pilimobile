package com.pilitejeamigurumis.restcontrollers.dto.boleta;

import com.pilitejeamigurumis.entities.BoletaDetalle;

public class BoletaDetalleLineaDto {

    private Long id;
    private String nombreProducto;
    private Integer cantidad;
    private Integer precioUnitario;

    public static BoletaDetalleLineaDto fromEntity(BoletaDetalle bd) {
        BoletaDetalleLineaDto dto = new BoletaDetalleLineaDto();
        dto.id       = bd.getId();

        if (bd.getProducto() != null) {
            dto.nombreProducto = bd.getProducto().getNombre();
        } else {
            dto.nombreProducto = "(producto eliminado)";
        }

        dto.cantidad       = bd.getCantidad() != null ? bd.getCantidad() : 0;
        dto.precioUnitario = bd.getPrecioUnit() != null ? bd.getPrecioUnit() : 0;

        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Integer getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Integer precioUnitario) { this.precioUnitario = precioUnitario; }
}
