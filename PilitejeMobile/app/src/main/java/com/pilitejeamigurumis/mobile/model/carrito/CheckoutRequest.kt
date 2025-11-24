package com.pilitejeamigurumis.mobile.model.carrito

data class CheckoutRequest(
    val direccionEnvio: String,
    val comentarios: String? = null
)
