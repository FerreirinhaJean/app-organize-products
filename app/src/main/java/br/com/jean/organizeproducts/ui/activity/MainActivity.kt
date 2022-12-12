package br.com.jean.organizeproducts.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.jean.organizeproducts.R
import br.com.jean.organizeproducts.databinding.ActivityMainBinding
import br.com.jean.organizeproducts.db.AppDatabase
import br.com.jean.organizeproducts.ui.recyclerview.adapter.ProductListAdapter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }
    private val productAdapter = ProductListAdapter(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView = binding.activityMainRecyclerview
        recyclerView.adapter = productAdapter

        lifecycleScope.launch {
            Log.i("MainActivity", "onCreate: BEFORE")
            val all = productDao.getAll()
            Log.i("MainActivity", "onCreate: ${all}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_activity_add -> {
                val intent = Intent(this, RegisterProductsActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onOptionsItemSelected(item)
    }


}