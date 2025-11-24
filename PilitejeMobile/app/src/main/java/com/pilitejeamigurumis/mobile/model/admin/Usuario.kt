package com.pilitejeamigurumis.mobile.model.admin

data class Usuario(
    val id: Long,
    val nombre: String,
    val email: String,
    val role: String,
    val activo: Boolean   // true = habilitado, false = bloqueado
)
