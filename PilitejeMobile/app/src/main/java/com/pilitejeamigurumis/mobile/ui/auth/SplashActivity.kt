package com.pilitejeamigurumis.mobile.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper

import com.pilitejeamigurumis.mobile.utils.SessionManager
import com.pilitejeamigurumis.mobile.ui.admin.AdminMainActivity
import com.pilitejeamigurumis.mobile.ui.cliente.ClienteMainActivity


// package com.pilitejeamigurumis.mobile.ui.auth

class SplashActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        // Puedes tener un layout simple con logo, pero no es obligatorio
        // setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            navigateNext()
        }, 800) // mini delay para que no sea tan brusco
    }

    private fun navigateNext() {
        val intent = when {
            !sessionManager.isLoggedIn() -> {
                Intent(this, LoginActivity::class.java)
            }

            sessionManager.getRole()?.contains("ADMIN") == true -> {
                Intent(this, AdminMainActivity::class.java)
            }

            else -> {
                Intent(this, ClienteMainActivity::class.java)
            }
        }

        startActivity(intent)
        finish()
    }
}

