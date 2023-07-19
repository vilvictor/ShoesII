package com.example.shoes.remote.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "shoes_details_table")
data class ShoesDetailEntity(

    @PrimaryKey
    val id:String,
    val name:String,
    val origen:String,
    val imageLink:String,
    val marca:String,
    val numero:String,
    val precio:String,
    val entrega:String
)