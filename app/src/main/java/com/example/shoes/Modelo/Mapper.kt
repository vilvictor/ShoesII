package com.example.shoes.Modelo

import com.example.shoes.remote.local.entity.ShoesDetailEntity
import com.example.shoes.remote.local.entity.ShoesEntity
import com.example.shoes.remote.net.Shoes
import com.example.shoes.remote.net.ShoesDetail


fun fromInternetToShoesEntity(shoesList: List<Shoes>): List<ShoesEntity>{

    return shoesList.map {

        ShoesEntity(
            id= it.id,
            name = it.name,
            origen = it.origen,
            imageLink = it.imageLink,
            marca = it.marca,
            numero = it.numero
        )
    }
}
fun fromInternetToShoesDetailEntity(shoes: ShoesDetail): ShoesDetailEntity {

    return ShoesDetailEntity(
        id=shoes.id,
        name = shoes.name,
        origen = shoes.origen,
        imageLink = shoes.imageLink,
        marca = shoes.marca,
        numero  = shoes.numero,
        precio = shoes.precio,
        entrega = shoes.entrega,

        )
}

