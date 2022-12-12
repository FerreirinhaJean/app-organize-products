package br.com.jean.organizeproducts.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.EventLogTags.Description
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import br.com.jean.organizeproducts.databinding.ActivityRegisterProductsBinding
import br.com.jean.organizeproducts.db.AppDatabase
import br.com.jean.organizeproducts.model.Product
import kotlinx.coroutines.launch

class RegisterProductsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRegisterProductsBinding.inflate(layoutInflater)
    }
    private val btSave by lazy {
        binding.activityRegisterProductsSave
    }
    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btSave.setOnClickListener {
            val name = binding.activityRegisterProductsName.editText?.text.toString()
            val description = binding.activityRegisterProductsDescription.editText?.text.toString()
            val price = binding.activityRegisterProductsPrice.editText?.text.toString()

            if (verifyFields(name, description, price)) {
                val product = Product(
                    name = name,
                    description = description,
                    price = convertStringtoDouble(price)
                )

                lifecycleScope.launch {
                    productDao.saveObject(product)
                }
            }
        }
    }


    private fun verifyFields(name: String, description: String, price: String): Boolean {
        if (name.isNullOrBlank()) {
            Toast.makeText(this, "Informe o nome do produto!", Toast.LENGTH_SHORT).show()
            binding.activityRegisterProductsName.requestFocus()
            return false
        }

        if (description.isNullOrBlank()) {
            Toast.makeText(this, "Informe a descrição do produto!", Toast.LENGTH_SHORT).show()
            binding.activityRegisterProductsDescription.requestFocus()
            return false
        }

        if (price.isNullOrBlank()) {
            Toast.makeText(this, "Informe o preço do produto!", Toast.LENGTH_SHORT).show()
            binding.activityRegisterProductsPrice.requestFocus()
        }

        return true
    }

    private fun convertStringtoDouble(value: String): Double {
        return value.toDouble()
    }


}