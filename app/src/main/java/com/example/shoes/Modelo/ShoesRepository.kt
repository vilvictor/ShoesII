package com.example.shoes.Modelo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shoes.remote.local.ShoesDao
import com.example.shoes.remote.local.entity.ShoesDetailEntity
import com.example.shoes.remote.RetrofitClient


class ShoesRepository (private val shoesDao: ShoesDao) {

    private val networkService = RetrofitClient.retrofitInstance()


    val shoesListLiveData = shoesDao.getAllShoes()

    val shoesDetailLiveData = MutableLiveData<ShoesDetailEntity>()




    suspend fun  fechShoes(){

        val service = kotlin.runCatching { networkService.fecthShoesList()}

        service.onSuccess {

            when(it.code()){
                in 200..299-> it.body()?.let {
                    // insertando la lista de cursos
                    shoesDao.insertAllShoes(fromInternetToShoesEntity(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error","${it.message}")
            }

        }

    }

    suspend fun fetchShoesDetail(id: String): ShoesDetailEntity? {
        val service = kotlin.runCatching { networkService.fechShoesDetail(id) }
        return service.getOrNull()?.body()?.let { shoesDetail ->
            // guardp los datos que viene del mapper y luego se los paso a dao directo
            val shoesDetailEntity = fromInternetToShoesDetailEntity(shoesDetail)
            //inserto los detalles de los curso DEL REPOSITORIO
            shoesDao.insertShoesDetail(shoesDetailEntity)
            shoesDetailEntity
        }
    }

}