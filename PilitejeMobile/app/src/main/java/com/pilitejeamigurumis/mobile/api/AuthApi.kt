package com.pilitejeamigurumis.mobile.api

import com.pilitejeamigurumis.mobile.model.auth.LoginRequest
import com.pilitejeamigurumis.mobile.model.auth.LoginResponse
import com.pilitejeamigurumis.mobile.model.perfil.RegistroRequest
import retrofit2.http.*
import retrofit2.Call



interface AuthApi {

    @POST("auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body body: RegistroRequest): Call<Any> // si necesitas
}
