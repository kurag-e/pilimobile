package com.pilitejeamigurumis.restcontrollers.dto.boleta;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.pilitejeamigurumis.entities.Boleta;

public class BoletaDto {

    private Long id;
    private Long numero;
    private LocalDateTime fecha;
    private Integer subtotal;
    private Integer iva;
    private Integer total;

    private String nombre;
    private String email;
    private String region;
    private String comuna;
    private String direccion;
    private String estadoPago;

    private List<BoletaDetalleLineaDto> detalles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getNumero() { return numero; }
    public void setNumero(Long numero) { this.numero = numero; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Integer getSubtotal() { return subtotal; }
    public void setSubtotal(Integer subtotal) { this.subtotal = subtotal; }

    public Integer getIva() { return iva; }
    public void setIva(Integer iva) { this.iva = iva; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    public List<BoletaDetalleLineaDto> getDetalles() { return detalles; }
    public void setDetalles(List<BoletaDetalleLineaDto> detalles) { this.detalles = detalles; }

    //mapper
    public static BoletaDto fromEntity(Boleta b) {
        BoletaDto dto = new BoletaDto();

        dto.id         = b.getId();
        dto.numero     = b.getNumero();
        dto.fecha      = b.getFecha();
        dto.subtotal   = b.getSubtotal();
        dto.iva        = b.getIva();
        dto.total      = b.getTotal();

        dto.nombre     = b.getNombre();
        dto.email      = b.getEmail();
        dto.region     = b.getRegion();
        dto.comuna     = b.getComuna();
        dto.direccion  = b.getDireccion();
        dto.estadoPago = b.getEstadoPago();

        if (b.getDetalles() != null && !b.getDetalles().isEmpty()) {
            dto.detalles = b.getDetalles()
                    .stream()
                    .map(BoletaDetalleLineaDto::fromEntity)
                    .toList();
        } else {
            dto.detalles = Collections.emptyList();
        }

        return dto;
    }
}
