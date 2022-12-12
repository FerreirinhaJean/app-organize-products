package br.com.jean.organizeproducts.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.jean.organizeproducts.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    suspend fun getAll(): List<Product>

    @Insert
    suspend fun saveObject(product: Product)

}