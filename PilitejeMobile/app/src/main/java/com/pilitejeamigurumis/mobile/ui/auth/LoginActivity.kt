package com.pilitejeamigurumis.mobile.ui.auth

import com.pilitejeamigurumis.mobile.api.ApiClient
import com.pilitejeamigurumis.mobile.api.AuthApi
import com.pilitejeamigurumis.mobile.model.auth.LoginRequest
import com.pilitejeamigurumis.mobile.model.auth.LoginResponse
import com.pilitejeamigurumis.mobile.ui.admin.AdminMainActivity
import com.pilitejeamigurumis.mobile.ui.cliente.ClienteMainActivity
import com.pilitejeamigurumis.mobile.utils.SessionManager
import com.pilitejeamigurumis.mobile.databinding.ActivityLoginBinding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var authApi: AuthApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        val retrofit = ApiClient.getRetrofit(this)
        authApi = retrofit.create(AuthApi::class.java)

        binding.btnLogin.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        val email = binding.edtEmail.text.toString().trim()
        val pass = binding.edtPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.edtEmail.error = "Ingresa tu correo"
            return
        }
        if (pass.isEmpty()) {
            binding.edtPassword.error = "Ingresa tu contraseña"
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false

        val body = LoginRequest(email, pass)
        authApi.login(body).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                binding.btnLogin.isEnabled = true

                if (response.isSuccessful) {
                    val data = response.body()!!
                    sessionManager.saveSession(
                        data.accessToken,
                        data.role,
                        data.id,
                        data.nombre,
                        data.email
                    )

                    val intent = if (data.role.contains("ADMIN")) {
                        Intent(this@LoginActivity, AdminMainActivity::class.java)
                    } else {
                        Intent(this@LoginActivity, ClienteMainActivity::class.java)
                    }

                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Credenciales inválidas o error en el servidor",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.btnLogin.isEnabled = true
                Toast.makeText(
                    this@LoginActivity,
                    "Error de red: ${t.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
