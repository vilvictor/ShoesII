package com.example.shoes.remote


import com.example.shoes.remote.net.Shoes
import com.example.shoes.remote.net.ShoesDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ShoesApi {

    @GET("shoes")
    suspend fun fecthShoesList(): Response<List<Shoes>>

    @GET("shoes/{id}")
    suspend fun fechShoesDetail(@Path("id") id: String): Response<ShoesDetail>
}
