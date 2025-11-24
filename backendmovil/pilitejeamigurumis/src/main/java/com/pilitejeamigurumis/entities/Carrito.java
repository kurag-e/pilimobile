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
@Table(name = "carrito")
public class Carrito {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario; // null si es invitado

  @Column(name = "session_id")
  private String sessionId; // para invitados

  @Column(name = "creado_en")
  private LocalDateTime creadoEn;

  @Column(name = "actualizado_en")
  private LocalDateTime actualizadoEn;

  @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CarritoItem> items = new ArrayList<>();


  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Usuario getUsuario() { return usuario; }
  public void setUsuario(Usuario usuario) { this.usuario = usuario; }

  public String getSessionId() { return sessionId; }
  public void setSessionId(String sessionId) { this.sessionId = sessionId; }

  public LocalDateTime getCreadoEn() { return creadoEn; }
  public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }

  public LocalDateTime getActualizadoEn() { return actualizadoEn; }
  public void setActualizadoEn(LocalDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }

  public List<CarritoItem> getItems() { return items; }
  public void setItems(List<CarritoItem> items) { this.items = items; }
}
