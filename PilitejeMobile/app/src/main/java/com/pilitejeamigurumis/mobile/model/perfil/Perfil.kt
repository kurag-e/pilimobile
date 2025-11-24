package com.pilitejeamigurumis.mobile.model.perfil

data class Perfil(
    val id: Long,
    val nombre: String,
    val email: String,
    val telefono: String?,
    val direccion: String?
)
