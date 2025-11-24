package com.pilitejeamigurumis.mobile.model.carrito

data class CarritoResponse(
    val items: List<CarritoItem>,
    val total: Int
)
