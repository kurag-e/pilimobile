package com.pilitejeamigurumis.restcontrollers.dto.auth;

public class RegistroRequest {

  private String nombre;
  private String email;
  private String password;
  private String region;
  private String comuna;
  private String direccion;
  private String rol;

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getRegion() { return region; }
  public void setRegion(String region) { this.region = region; }

  public String getComuna() { return comuna; }
  public void setComuna(String comuna) { this.comuna = comuna; }

  public String getDireccion() { return direccion; }
  public void setDireccion(String direccion) { this.direccion = direccion; }

  public String getRol() { return rol; }
  public void setRol(String rol) { this.rol = rol; }
}
