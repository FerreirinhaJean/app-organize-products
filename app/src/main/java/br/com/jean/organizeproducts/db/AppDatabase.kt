package br.com.jean.organizeproducts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.jean.organizeproducts.db.dao.ProductDao
import br.com.jean.organizeproducts.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {

        @Volatile
        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "organize_products.db"
            ).addMigrations()
                .build().also { db = it }
        }
    }

}