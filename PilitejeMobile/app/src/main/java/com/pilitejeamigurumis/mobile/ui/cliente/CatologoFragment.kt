package com.pilitejeamigurumis.mobile.ui.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.api.ProductoApi
import com.pilitejeamigurumis.mobile.databinding.FragmentCatalogoBinding
import com.pilitejeamigurumis.mobile.model.producto.Producto
import com.pilitejeamigurumis.mobile.ui.adapter.ProductoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatalogoFragment : Fragment() {

    private var _binding: FragmentCatalogoBinding? = null
    private val binding get() = _binding!!

    private lateinit var productoApi: ProductoApi
    private lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = ApiClient.getRetrofit(requireContext())
        productoApi = retrofit.create(ProductoApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ProductoAdapter(mutableListOf()) { producto ->
            // Aquí llamas a tu CarritoApi.addItem(producto)
            Toast.makeText(requireContext(), "Agregar ${producto.nombre}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProductos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductos.adapter = adapter

        cargarProductos()
    }

    private fun cargarProductos() {
        binding.progressBar.visibility = View.VISIBLE
        binding.txtEmpty.visibility = View.GONE
        binding.rvProductos.visibility = View.GONE

        productoApi.getProductos().enqueue(object : Callback<List<Producto>> {
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                binding.progressBar.visibility = View.GONE

                if (response.isSuccessful) {
                    val list = response.body().orEmpty()
                    if (list.isEmpty()) {
                        binding.txtEmpty.visibility = View.VISIBLE
                    } else {
                        binding.rvProductos.visibility = View.VISIBLE
                        adapter.setData(list)
                    }
                } else {
                    binding.txtEmpty.text = "Error al cargar productos"
                    binding.txtEmpty.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.txtEmpty.text = "Error de red, toca para reintentar"
                binding.txtEmpty.visibility = View.VISIBLE

                binding.txtEmpty.setOnClickListener {
                    cargarProductos()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
