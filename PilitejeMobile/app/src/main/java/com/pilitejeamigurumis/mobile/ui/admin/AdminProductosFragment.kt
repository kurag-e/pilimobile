package com.pilitejeamigurumis.mobile.ui.admin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pilitejeamigurumis.mobile.api.AdminApi
import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.databinding.FragmentAdminProductosBinding
import com.pilitejeamigurumis.mobile.model.producto.Producto
import com.pilitejeamigurumis.mobile.ui.adapter.ProductoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminProductosFragment : Fragment() {

    private var _binding: FragmentAdminProductosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adminApi: AdminApi
    private lateinit var adapter: ProductoAdapter // puedes crear otro adapter si quieres

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminApi = ApiClient.getRetrofit(requireContext()).create(AdminApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminProductosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductoAdapter(mutableListOf()) { /* onAddToCart no aplica acá */ }

        binding.rvProductosAdmin.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductosAdmin.adapter = adapter

        binding.fabAgregarProducto.setOnClickListener {
            startActivity(Intent(requireContext(), AdminProductoFormActivity::class.java))
        }

        // TODO: Llamar a /admin/productos cuando lo tengas
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
