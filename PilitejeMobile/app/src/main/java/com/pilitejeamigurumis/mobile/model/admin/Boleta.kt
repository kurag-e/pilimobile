package com.pilitejeamigurumis.mobile.model.admin

data class Boleta(
    val id: Long,
    val fecha: String,
    val total: Int,
    val estado: String,       // PENDIENTE / ACEPTADO / RECHAZADO / ENVIADO
    val clienteNombre: String?
)
