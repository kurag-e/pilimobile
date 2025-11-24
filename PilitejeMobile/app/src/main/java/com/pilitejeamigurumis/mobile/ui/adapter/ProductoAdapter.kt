package com.pilitejeamigurumis.mobile.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pilitejeamigurumis.mobile.R
import com.pilitejeamigurumis.mobile.model.producto.Producto


class ProductoAdapter(
    private val productos: MutableList<Producto>,
    private val onAddToCartClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProducto: ImageView = view.findViewById(R.id.imgProducto)
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtPrecio: TextView = view.findViewById(R.id.txtPrecio)
        val btnAgregar: Button = view.findViewById(R.id.btnAgregarCarrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item = productos[position]
        holder.txtNombre.text = item.nombre
        holder.txtPrecio.text = "$${item.precio}"

        // Si usas Glide:
        Glide.with(holder.itemView.context)
            .load(item.imagen)
            .into(holder.imgProducto)

        holder.btnAgregar.setOnClickListener {
            onAddToCartClick(item)
        }
    }

    override fun getItemCount(): Int = productos.size

    fun setData(newList: List<Producto>) {
        productos.clear()
        productos.addAll(newList)
        notifyDataSetChanged()
    }
}
