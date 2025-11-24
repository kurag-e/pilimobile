package com.pilitejeamigurumis.restcontrollers.dto.carrito;

public class CheckoutRequest {

  private String nombre;
  private String email;
  private String region;
  private String comuna;
  private String direccion;

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
}
