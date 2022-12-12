package br.com.jean.organizeproducts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val description: String,
    val price: Double
) {
}