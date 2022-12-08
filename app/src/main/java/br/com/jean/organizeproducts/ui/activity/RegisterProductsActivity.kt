package br.com.jean.organizeproducts.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.jean.organizeproducts.databinding.ActivityRegisterProductsBinding

class RegisterProductsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterProductsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}