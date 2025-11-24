package com.pilitejeamigurumis.mobile.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pilitejeamigurumis.mobile.api.AdminApi
import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.databinding.FragmentAdminUsuariosBinding
import com.pilitejeamigurumis.mobile.model.admin.Usuario
import com.pilitejeamigurumis.mobile.ui.adapter.UsuarioAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminUsuariosFragment : Fragment() {

    private var _binding: FragmentAdminUsuariosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adminApi: AdminApi
    private lateinit var adapter: UsuarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminApi = ApiClient.getRetrofit(requireContext()).create(AdminApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminUsuariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UsuarioAdapter(mutableListOf()) { usuario ->
            // TODO: según usuario.activo llamar a bloquear/desbloquear
        }

        binding.rvUsuariosAdmin.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsuariosAdmin.adapter = adapter

        // TODO: llamar a adminApi.getUsuarios() y cargar en adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
