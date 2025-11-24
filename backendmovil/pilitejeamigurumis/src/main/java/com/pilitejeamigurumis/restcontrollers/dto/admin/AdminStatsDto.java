package com.pilitejeamigurumis.restcontrollers.dto.admin;

public class AdminStatsDto {
    private long totalUsuarios;
    private long totalClientes;
    private long totalAdmins;
    private long totalVendedores;
    private long totalProductos;
    private long stockTotal;
    private long totalBoletas;
    private long totalVentas;
    
    public long getTotalUsuarios() {
        return totalUsuarios;
    }
    public void setTotalUsuarios(long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }
    public long getTotalClientes() {
        return totalClientes;
    }
    public void setTotalClientes(long totalClientes) {
        this.totalClientes = totalClientes;
    }
    public long getTotalAdmins() {
        return totalAdmins;
    }
    public void setTotalAdmins(long totalAdmins) {
        this.totalAdmins = totalAdmins;
    }
    public long getTotalVendedores() {
        return totalVendedores;
    }
    public void setTotalVendedores(long totalVendedores) {
        this.totalVendedores = totalVendedores;
    }
    public long getTotalProductos() {
        return totalProductos;
    }
    public void setTotalProductos(long totalProductos) {
        this.totalProductos = totalProductos;
    }
    public long getStockTotal() {
        return stockTotal;
    }
    public void setStockTotal(long stockTotal) {
        this.stockTotal = stockTotal;
    }
    public long getTotalBoletas() {
        return totalBoletas;
    }
    public void setTotalBoletas(long totalBoletas) {
        this.totalBoletas = totalBoletas;
    }
    public long getTotalVentas() {
        return totalVentas;
    }
    public void setTotalVentas(long totalVentas) {
        this.totalVentas = totalVentas;
    }

}
