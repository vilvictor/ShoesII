package com.example.shoes.remote.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoes.remote.local.entity.ShoesDetailEntity
import com.example.shoes.remote.local.entity.ShoesEntity

@Dao
interface ShoesDao {


    // insertar lista

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllShoes(listShoes: List<ShoesEntity>)


    @Query("SELECT * FROM shoes_list_table  ORDER BY id ASC")
    fun getAllShoes(): LiveData<List<ShoesEntity>>


    // INSERTA UN ELEMENTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoesDetail(shoes: ShoesDetailEntity)


    // da error por momentanemante no se ocupa
    //@Query("SELECT * FROM course_details_table  ORDER BY id ASC")
    //suspend fun  getCourseDetailByID(id:String): CoursesDetailEntity?



}