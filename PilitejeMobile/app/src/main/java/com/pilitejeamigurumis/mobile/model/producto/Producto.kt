package com.pilitejeamigurumis.mobile.model.producto

data class Producto(
    val id: Long,
    val nombre: String,
    val descripcion: String?,
    val precio: Int,
    val stock: Int,
    val imagen: String? // URL principal
)

data class Categoria(
    val id: Long,
    val nombre: String
)