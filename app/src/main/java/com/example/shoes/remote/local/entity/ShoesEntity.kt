package com.example.shoes.remote.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName ="shoes_list_table")
data class ShoesEntity(

    @PrimaryKey
    val id:String,
    val name:String,
    val origen:String,
    val imageLink:String,
    val marca:String,
    val numero:String



)