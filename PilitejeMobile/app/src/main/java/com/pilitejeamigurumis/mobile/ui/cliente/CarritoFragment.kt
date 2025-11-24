package com.pilitejeamigurumis.mobile.ui.cliente

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.api.CarritoApi
import com.pilitejeamigurumis.mobile.databinding.FragmentCarritoBinding
import com.pilitejeamigurumis.mobile.model.carrito.*
import com.pilitejeamigurumis.mobile.ui.cliente.CarritoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarritoFragment : Fragment() {

    private var _binding: FragmentCarritoBinding? = null
    private val binding get() = _binding!!

    private lateinit var carritoApi: CarritoApi
    private lateinit var adapter: CarritoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = ApiClient.getRetrofit(requireContext())
        carritoApi = retrofit.create(CarritoApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarritoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = CarritoAdapter(mutableListOf(),
            onIncrease = { item -> actualizarCantidad(item, item.cantidad + 1) },
            onDecrease = { item ->
                val nueva = (item.cantidad - 1).coerceAtLeast(1)
                actualizarCantidad(item, nueva)
            },
            onDelete = { item -> eliminarItem(item) }
        )

        binding.rvCarrito.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCarrito.adapter = adapter

        binding.btnPagar.setOnClickListener {
            mostrarDialogoCheckout()
        }

        cargarCarrito()
    }

    private fun cargarCarrito() {
        binding.progressBar.visibility = View.VISIBLE
        carritoApi.getCarrito().enqueue(object : Callback<CarritoResponse> {
            override fun onResponse(
                call: Call<CarritoResponse>,
                response: Response<CarritoResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val data = response.body()!!
                    adapter.setData(data.items)
                    binding.txtTotal.text = "Total: $${data.total}"
                } else {
                    Toast.makeText(requireContext(), "Error al cargar carrito", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<CarritoResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Error de red", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun actualizarCantidad(item: CarritoItem, nuevaCantidad: Int) {
        val req = AddCarritoItemRequest(item.producto.id, nuevaCantidad)
        carritoApi.addItem(req).enqueue(object : Callback<CarritoResponse> {
            override fun onResponse(
                call: Call<CarritoResponse>,
                response: Response<CarritoResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()!!
                    adapter.setData(data.items)
                    binding.txtTotal.text = "Total: $${data.total}"
                }
            }

            override fun onFailure(call: Call<CarritoResponse>, t: Throwable) {}
        })
    }

    private fun eliminarItem(item: CarritoItem) {
        AlertDialog.Builder(requireContext())
            .setTitle("Quitar producto")
            .setMessage("¿Seguro que quieres quitar ${item.producto.nombre} del carrito?")
            .setPositiveButton("Sí") { _, _ ->
                carritoApi.deleteItem(item.id).enqueue(object : Callback<CarritoResponse> {
                    override fun onResponse(
                        call: Call<CarritoResponse>,
                        response: Response<CarritoResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()!!
                            adapter.setData(data.items)
                            binding.txtTotal.text = "Total: $${data.total}"
                        }
                    }

                    override fun onFailure(call: Call<CarritoResponse>, t: Throwable) {}
                })
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarDialogoCheckout() {
        val input = EditText(requireContext())
        input.hint = "Dirección de envío"

        AlertDialog.Builder(requireContext())
            .setTitle("Confirmar pago")
            .setView(input)
            .setPositiveButton("Pagar") { _, _ ->
                val direccion = input.text.toString().trim()
                if (direccion.isNotEmpty()) {
                    hacerCheckout(direccion)
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun hacerCheckout(direccion: String) {
        val body = CheckoutRequest(direccionEnvio = direccion)
        carritoApi.checkout(body).enqueue(object : Callback<CheckoutResponse> {
            override fun onResponse(
                call: Call<CheckoutResponse>,
                response: Response<CheckoutResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Pago registrado. Boleta #${response.body()!!.boletaId}",
                        Toast.LENGTH_LONG
                    ).show()
                    cargarCarrito()
                }
            }

            override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error al procesar pago", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
