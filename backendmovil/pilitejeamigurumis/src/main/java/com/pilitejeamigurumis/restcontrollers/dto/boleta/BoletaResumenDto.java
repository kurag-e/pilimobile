package com.pilitejeamigurumis.restcontrollers.dto.boleta;

import java.time.LocalDateTime;

import com.pilitejeamigurumis.entities.Boleta;

public class BoletaResumenDto {

    private Long id;
    private Long numero;
    private LocalDateTime fecha;
    private String nombre;
    private Integer total;
    private String estadoPago;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getNumero() { return numero; }
    public void setNumero(Long numero) { this.numero = numero; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    public static BoletaResumenDto fromEntity(Boleta b) {
        BoletaResumenDto dto = new BoletaResumenDto();
        dto.id = b.getId();
        dto.numero = b.getNumero();
        dto.fecha = b.getFecha();
        dto.nombre = b.getNombre();
        dto.total = b.getTotal();
        dto.estadoPago = b.getEstadoPago();
        return dto;
    }
}
