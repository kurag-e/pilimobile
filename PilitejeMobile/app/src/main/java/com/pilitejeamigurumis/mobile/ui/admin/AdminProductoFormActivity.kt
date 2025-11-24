package com.pilitejeamigurumis.mobile.ui.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pilitejeamigurumis.mobile.databinding.ActivityAdminProductoFormBinding

class AdminProductoFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProductoFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProductoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            // TODO: llamar a endpoint de crear/editar producto
            Toast.makeText(this, "Guardar producto (todo)", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
