package com.pilitejeamigurumis.mobile.api

import com.pilitejeamigurumis.mobile.model.carrito.AddCarritoItemRequest
import com.pilitejeamigurumis.mobile.model.carrito.CarritoResponse
import com.pilitejeamigurumis.mobile.model.carrito.CheckoutRequest
import com.pilitejeamigurumis.mobile.model.carrito.CheckoutResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface CarritoApi {

    @GET("cliente/carrito")
    fun getCarrito(): Call<CarritoResponse>

    @POST("cliente/carrito/items")
    fun addItem(@Body body: AddCarritoItemRequest): Call<CarritoResponse>

    @HTTP(method = "DELETE", path = "cliente/carrito/items/{id}", hasBody = false)
    fun deleteItem(@Path("id") id: Long): Call<CarritoResponse>

    @POST("cliente/carrito/checkout")
    fun checkout(@Body body: CheckoutRequest): Call<CheckoutResponse>
}
