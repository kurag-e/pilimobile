package com.pilitejeamigurumis.mobile.model.carrito

data class CheckoutResponse(
    val boletaId: Long,
    val total: Int,
    val estado: String            // PENDIENTE / ACEPTADO / RECHAZADO / ENVIADO
)
