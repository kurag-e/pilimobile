package com.pilitejeamigurumis.restcontrollers.dto.perfil;

public class PasswordChangeRequest {

    private String actualPassword;
    private String nuevaPassword;

    public String getActualPassword() { return actualPassword; }
    public void setActualPassword(String actualPassword) { this.actualPassword = actualPassword; }

    public String getNuevaPassword() { return nuevaPassword; }
    public void setNuevaPassword(String nuevaPassword) { this.nuevaPassword = nuevaPassword; }
}
