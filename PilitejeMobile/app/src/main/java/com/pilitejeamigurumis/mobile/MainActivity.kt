package com.pilitejeamigurumis.mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pilitejeamigurumis.mobile.ui.admin.AdminMainActivity
import com.pilitejeamigurumis.mobile.ui.cliente.ClienteMainActivity
import com.pilitejeamigurumis.mobile.ui.auth.LoginActivity
import com.pilitejeamigurumis.mobile.utils.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sessionManager = SessionManager(this)

        // NO usamos layout, esta Activity solo decide a dónde ir
        if (sessionManager.isLoggedIn()) {
            when (sessionManager.getRole()) {
                "ADMIN" -> {
                    startActivity(Intent(this, AdminMainActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, ClienteMainActivity::class.java))
                }
            }
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Cerramos MainActivity para que no se pueda volver con back
        finish()
    }
}
