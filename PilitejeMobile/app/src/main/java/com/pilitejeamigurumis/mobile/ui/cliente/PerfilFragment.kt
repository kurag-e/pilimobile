package com.pilitejeamigurumis.mobile.ui.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.api.PerfilApi
import com.pilitejeamigurumis.mobile.databinding.FragmentPerfilBinding
import com.pilitejeamigurumis.mobile.model.perfil.Perfil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    private lateinit var perfilApi: PerfilApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        perfilApi = ApiClient.getRetrofit(requireContext()).create(PerfilApi::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnGuardar.setOnClickListener { guardarPerfil() }
        cargarPerfil()
    }

    private fun cargarPerfil() {
        perfilApi.getPerfil().enqueue(object : Callback<Perfil> {
            override fun onResponse(call: Call<Perfil>, response: Response<Perfil>) {
                if (response.isSuccessful) {
                    val p = response.body()!!
                    binding.edtNombre.setText(p.nombre)
                    binding.edtEmail.setText(p.email)
                    binding.edtTelefono.setText(p.telefono ?: "")
                    binding.edtDireccion.setText(p.direccion ?: "")
                }
            }

            override fun onFailure(call: Call<Perfil>, t: Throwable) {}
        })
    }

    private fun guardarPerfil() {
        val perfil = Perfil(
            id = 0L, // el backend puede ignorar o usar el que tenga en token
            nombre = binding.edtNombre.text.toString(),
            email = binding.edtEmail.text.toString(),
            telefono = binding.edtTelefono.text.toString(),
            direccion = binding.edtDireccion.text.toString()
        )

        perfilApi.updatePerfil(perfil).enqueue(object : Callback<Perfil> {
            override fun onResponse(call: Call<Perfil>, response: Response<Perfil>) {
                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "Perfil actualizado", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Perfil>, t: Throwable) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
