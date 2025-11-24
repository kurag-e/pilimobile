package com.pilitejeamigurumis.mobile.api

import com.pilitejeamigurumis.mobile.model.perfil.Perfil
import com.pilitejeamigurumis.mobile.model.perfil.RegistroRequest
import com.pilitejeamigurumis.mobile.model.perfil.RegistroResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST

interface PerfilApi {

    @GET("cliente/perfil")
    fun getPerfil(): Call<Perfil>

    @PUT("cliente/perfil")
    fun updatePerfil(@Body body: Perfil): Call<Perfil>

    @POST("auth/register")
    fun register(@Body body: RegistroRequest): Call<RegistroResponse>
}
