package com.pilitejeamigurumis.mobile.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pilitejeamigurumis.mobile.R
import com.pilitejeamigurumis.mobile.databinding.ActivityAdminMainBinding

class AdminMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(AdminDashboardFragment())
        }

        binding.bottomNavAdmin.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> replaceFragment(AdminDashboardFragment())
                R.id.nav_productos -> replaceFragment(AdminProductosFragment())
                R.id.nav_usuarios -> replaceFragment(AdminUsuariosFragment())
                R.id.nav_ordenes -> replaceFragment(AdminOrdenesFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.adminFragmentContainer.id, fragment)
            .commit()
    }
}
