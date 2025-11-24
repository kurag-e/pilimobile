package com.pilitejeamigurumis.mobile.ui.cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pilitejeamigurumis.mobile.R
import com.pilitejeamigurumis.mobile.model.carrito.CarritoItem

class CarritoAdapter(
    private val items: MutableList<CarritoItem>,
    private val onIncrease: (CarritoItem) -> Unit,
    private val onDecrease: (CarritoItem) -> Unit,
    private val onDelete: (CarritoItem) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    inner class CarritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProducto: ImageView = view.findViewById(R.id.imgProducto)
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtCantidad: TextView = view.findViewById(R.id.txtCantidad)
        val txtSubtotal: TextView = view.findViewById(R.id.txtSubtotal)
        val btnMas: ImageButton = view.findViewById(R.id.btnMas)
        val btnMenos: ImageButton = view.findViewById(R.id.btnMenos)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val item = items[position]
        holder.txtNombre.text = item.producto.nombre
        holder.txtCantidad.text = item.cantidad.toString()
        holder.txtSubtotal.text = "$${item.subtotal}"

        Glide.with(holder.itemView.context)
            .load(item.producto.imagen)
            .into(holder.imgProducto)

        holder.btnMas.setOnClickListener { onIncrease(item) }
        holder.btnMenos.setOnClickListener { onDecrease(item) }
        holder.btnEliminar.setOnClickListener { onDelete(item) }
    }

    override fun getItemCount(): Int = items.size

    fun setData(newItems: List<CarritoItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
