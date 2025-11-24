package com.pilitejeamigurumis.restcontrollers.dto.perfil;

import com.pilitejeamigurumis.entities.Usuario;

public class PerfilDto {

    private Long id;
    private String nombre;
    private String email;
    private String region;
    private String comuna;
    private String direccion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public static PerfilDto fromEntity(Usuario u) {
        PerfilDto dto = new PerfilDto();
        dto.setId(u.getId());
        dto.setNombre(u.getNombre());
        dto.setEmail(u.getEmail());
        dto.setRegion(u.getRegion());
        dto.setComuna(u.getComuna());
        dto.setDireccion(u.getDireccion());
        return dto;
    }

    public void mergeInto(Usuario u) {
        if (this.nombre != null)    u.setNombre(this.nombre);
        if (this.email != null)     u.setEmail(this.email);
        if (this.region != null)    u.setRegion(this.region);
        if (this.comuna != null)    u.setComuna(this.comuna);
        if (this.direccion != null) u.setDireccion(this.direccion);
    }
}
