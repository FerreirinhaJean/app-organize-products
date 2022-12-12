package br.com.jean.organizeproducts.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.jean.organizeproducts.databinding.ProductItemBinding
import br.com.jean.organizeproducts.model.Product
import java.text.NumberFormat
import java.util.*

class ProductListAdapter(
    products: List<Product> = emptyList(),
    private val context: Context
) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    private val products = products.toMutableList()

    inner class MyViewHolder(
        private val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product

        fun create(product: Product) {
            val name = binding.productItemName
            val description = binding.productItemDescription
            val price = binding.productItemPrice

            name.text = product.name
            description.text = product.description
            price.text = convertPriceToCurrency(product.price)
        }

        fun convertPriceToCurrency(value: Double): String =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(value)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        return MyViewHolder(ProductItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = products.get(position)
        holder.create(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}