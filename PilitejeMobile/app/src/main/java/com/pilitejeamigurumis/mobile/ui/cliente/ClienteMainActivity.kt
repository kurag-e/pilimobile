package com.pilitejeamigurumis.mobile.ui.cliente


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pilitejeamigurumis.mobile.databinding.ActivityClienteMainBinding
import com.pilitejeamigurumis.mobile.utils.SessionManager
import androidx.fragment.app.Fragment
import com.pilitejeamigurumis.mobile.R
import com.pilitejeamigurumis.mobile.ui.cliente.CarritoFragment
import com.pilitejeamigurumis.mobile.ui.cliente.CatalogoFragment
import com.pilitejeamigurumis.mobile.ui.cliente.PerfilFragment



class ClienteMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClienteMainBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClienteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // Fragment inicial: Catálogo
        if (savedInstanceState == null) {
            replaceFragment(CatalogoFragment())
        }

        binding.bottomNavCliente.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_catalogo -> replaceFragment(CatalogoFragment())
                R.id.nav_carrito -> replaceFragment(CarritoFragment())
                R.id.nav_perfil -> replaceFragment(PerfilFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.clienteFragmentContainer.id, fragment)
            .commit()
    }
}
