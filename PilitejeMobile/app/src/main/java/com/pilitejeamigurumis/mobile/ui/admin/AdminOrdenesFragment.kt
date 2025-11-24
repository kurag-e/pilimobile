package com.pilitejeamigurumis.mobile.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pilitejeamigurumis.mobile.api.AdminApi
import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.databinding.FragmentAdminOrdenesBinding
import com.pilitejeamigurumis.mobile.model.admin.Boleta
import com.pilitejeamigurumis.mobile.ui.adapter.BoletaAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminOrdenesFragment : Fragment() {

    private var _binding: FragmentAdminOrdenesBinding? = null
    private val binding get() = _binding!!

    private lateinit var adminApi: AdminApi
    private lateinit var adapter: BoletaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminApi = ApiClient.getRetrofit(requireContext()).create(AdminApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminOrdenesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BoletaAdapter(
            mutableListOf(),
            onAceptar = { /* TODO: adminApi.aceptarBoleta */ },
            onRechazar = { /* TODO: adminApi.rechazarBoleta */ },
            onEnviar = { /* TODO: adminApi.marcarEnviada */ }
        )

        binding.rvOrdenesAdmin.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrdenesAdmin.adapter = adapter

        // TODO: adminApi.getBoletas("PENDIENTE") y setData
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
