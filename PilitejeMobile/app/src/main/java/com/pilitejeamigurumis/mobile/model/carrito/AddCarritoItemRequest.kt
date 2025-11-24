package com.pilitejeamigurumis.mobile.model.carrito

data class AddCarritoItemRequest(
    val productoId: Long,
    val cantidad: Int
)
