package com.pilitejeamigurumis.mobile.model.carrito

import com.pilitejeamigurumis.mobile.model.producto.Producto

data class CarritoItem(
    val id: Long,                 // id del item en el carrito
    val producto: Producto,       // producto completo
    val cantidad: Int,
    val subtotal: Int             // precio * cantidad
)
