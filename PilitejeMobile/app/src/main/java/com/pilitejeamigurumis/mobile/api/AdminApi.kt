package com.pilitejeamigurumis.mobile.api

import com.pilitejeamigurumis.mobile.model.admin.AdminStats
import com.pilitejeamigurumis.mobile.model.admin.Boleta
import com.pilitejeamigurumis.mobile.model.admin.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AdminApi {

    // Dashboard
    @GET("admin/stats")
    fun getStats(): Call<AdminStats>

    // Usuarios
    @GET("admin/usuarios")
    fun getUsuarios(): Call<List<Usuario>>

    @PUT("admin/usuarios/{id}/bloquear")
    fun bloquearUsuario(@Path("id") id: Long): Call<Usuario>

    @PUT("admin/usuarios/{id}/desbloquear")
    fun desbloquearUsuario(@Path("id") id: Long): Call<Usuario>

    // Boletas / órdenes
    @GET("admin/boletas")
    fun getBoletas(
        @Query("estado") estado: String? = null  // null = todas, "PENDIENTE" = solo pendientes
    ): Call<List<Boleta>>

    @PUT("admin/boletas/{id}/aceptar")
    fun aceptarBoleta(@Path("id") id: Long): Call<Boleta>

    @PUT("admin/boletas/{id}/rechazar")
    fun rechazarBoleta(@Path("id") id: Long): Call<Boleta>

    @PUT("admin/boletas/{id}/enviar")
    fun marcarEnviada(@Path("id") id: Long): Call<Boleta>
}
