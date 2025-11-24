package com.pilitejeamigurumis.mobile.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pilitejeamigurumis.mobile.R
import com.pilitejeamigurumis.mobile.model.admin.Boleta

class BoletaAdapter(
    private val items: MutableList<Boleta>,
    private val onAceptar: (Boleta) -> Unit,
    private val onRechazar: (Boleta) -> Unit,
    private val onEnviar: (Boleta) -> Unit
) : RecyclerView.Adapter<BoletaAdapter.BoletaViewHolder>() {

    inner class BoletaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtId: TextView = view.findViewById(R.id.txtBoletaId)
        val txtCliente: TextView = view.findViewById(R.id.txtCliente)
        val txtTotal: TextView = view.findViewById(R.id.txtTotal)
        val txtEstado: TextView = view.findViewById(R.id.txtEstado)
        val btnAceptar: Button = view.findViewById(R.id.btnAceptar)
        val btnRechazar: Button = view.findViewById(R.id.btnRechazar)
        val btnEnviar: Button = view.findViewById(R.id.btnEnviar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoletaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_boleta, parent, false)
        return BoletaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoletaViewHolder, position: Int) {
        val item = items[position]
        holder.txtId.text = "#${item.id}"
        holder.txtCliente.text = item.clienteNombre ?: "Cliente"
        holder.txtTotal.text = "$${item.total}"
        holder.txtEstado.text = item.estado

        holder.btnAceptar.setOnClickListener { onAceptar(item) }
        holder.btnRechazar.setOnClickListener { onRechazar(item) }
        holder.btnEnviar.setOnClickListener { onEnviar(item) }

        // Mostrar/ocultar botones según estado
        val pendiente = item.estado == "PENDIENTE"
        holder.btnAceptar.visibility = if (pendiente) View.VISIBLE else View.GONE
        holder.btnRechazar.visibility = if (pendiente) View.VISIBLE else View.GONE
        holder.btnEnviar.visibility = if (item.estado == "ACEPTADO") View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int = items.size

    fun setData(newItems: List<Boleta>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
