package com.pilitejeamigurumis.mobile.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pilitejeamigurumis.mobile.R
import com.pilitejeamigurumis.mobile.model.admin.Usuario

class UsuarioAdapter(
    private val items: MutableList<Usuario>,
    private val onToggleActivo: (Usuario) -> Unit
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    inner class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtEmail: TextView = view.findViewById(R.id.txtEmail)
        val txtRole: TextView = view.findViewById(R.id.txtRole)
        val btnBloquear: Button = view.findViewById(R.id.btnBloquear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val item = items[position]
        holder.txtNombre.text = item.nombre
        holder.txtEmail.text = item.email
        holder.txtRole.text = item.role

        holder.btnBloquear.text = if (item.activo) "Bloquear" else "Desbloquear"

        holder.btnBloquear.setOnClickListener {
            onToggleActivo(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setData(newItems: List<Usuario>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
