package com.pilitejeamigurumis.mobile.model.auth

data class LoginResponse(
    val accessToken: String,
    val id: Long,
    val nombre: String,
    val email: String,
    val role: String
)