package com.pilitejeamigurumis.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "boleta")
public class Boleta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long numero;

  private LocalDateTime fecha;

  private Integer subtotal;
  private Integer iva;
  private Integer total;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario; // null si es invitado

  private String nombre;
  private String email;
  private String region;
  private String comuna;
  private String direccion;

  @Column(name = "estado_pago")
  private String estadoPago; // PAGADO, PENDIENTE, RECHAZADO

  @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BoletaDetalle> detalles = new ArrayList<>();

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

  public Usuario getUsuario() { return usuario; }
  public void setUsuario(Usuario usuario) { this.usuario = usuario; }

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

  public List<BoletaDetalle> getDetalles() { return detalles; }
  public void setDetalles(List<BoletaDetalle> detalles) { this.detalles = detalles; }
}
