package com.pilitejeamigurumis.mobile.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pilitejeamigurumis.mobile.api.AdminApi
import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.databinding.FragmentAdminDashboardBinding
import com.pilitejeamigurumis.mobile.model.admin.AdminStats
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminDashboardFragment : Fragment() {

    private var _binding: FragmentAdminDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adminApi: AdminApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminApi = ApiClient.getRetrofit(requireContext()).create(AdminApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargarStats()
    }

    private fun cargarStats() {
        adminApi.getStats().enqueue(object : Callback<AdminStats> {
            override fun onResponse(call: Call<AdminStats>, response: Response<AdminStats>) {
                if (response.isSuccessful) {
                    val s = response.body()!!
                    binding.txtTotalUsuarios.text = "Usuarios: ${s.totalUsuarios}"
                    binding.txtTotalProductos.text = "Productos: ${s.totalProductos}"
                    binding.txtPedidosPendientes.text = "Pendientes: ${s.pedidosPendientes}"
                }
            }

            override fun onFailure(call: Call<AdminStats>, t: Throwable) {
                // puedes loguear o mostrar un Toast si quieres
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
