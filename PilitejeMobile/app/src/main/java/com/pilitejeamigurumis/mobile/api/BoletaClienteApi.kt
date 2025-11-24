package com.pilitejeamigurumis.mobile.api

import com.pilitejeamigurumis.mobile.model.admin.Boleta
import retrofit2.Call
import retrofit2.http.GET

interface BoletaClienteApi {
    @GET("cliente/boletas")
    fun getHistorial(): Call<List<Boleta>>
}
