package com.example.shoes.remote.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoes.remote.local.ShoesDao
import com.example.shoes.remote.local.entity.ShoesDetailEntity
import com.example.shoes.remote.local.entity.ShoesEntity


@Database(entities = [ShoesEntity::class, ShoesDetailEntity::class], version = 2,
    exportSchema = false)
abstract class ShoesDataBase : RoomDatabase(){

    // representacion del dao
    abstract fun getShoesDao() : ShoesDao

    companion object{

        @Volatile
        private var
                INSTANCE : ShoesDataBase? = null
        fun getDataBase(context: Context) : ShoesDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoesDataBase::class.java, "zapateria")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}