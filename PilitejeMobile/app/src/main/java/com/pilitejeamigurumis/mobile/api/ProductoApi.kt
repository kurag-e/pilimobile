package com.pilitejeamigurumis.mobile.api

import com.pilitejeamigurumis.mobile.model.producto.Producto
import retrofit2.Call
import retrofit2.http.GET

interface ProductoApi {
    @GET("productos")
    fun getProductos(): Call<List<Producto>>
}
