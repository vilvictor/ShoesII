package com.example.shoes.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoes.Modelo.ShoesRepository
import com.example.shoes.remote.local.database.ShoesDataBase
import com.example.shoes.remote.local.entity.ShoesDetailEntity
import com.example.shoes.remote.local.entity.ShoesEntity

import kotlinx.coroutines.launch


class ShoesViewModel (application: Application): AndroidViewModel(application){

    // conexion repositorio
    private val repository : ShoesRepository

    // entidad
    private val shoesDetailLiveData = MutableLiveData<ShoesDetailEntity>()

    private  var idSelected: String ="-1"

    init{
        val bd= ShoesDataBase.getDataBase(application)
        val  shoesDao = bd.getShoesDao()

        repository = ShoesRepository(shoesDao)

        viewModelScope.launch {

            repository.fechShoes()
        }
    }

    fun getShoesList(): LiveData<List<ShoesEntity>> = repository.shoesListLiveData

    fun getShoesDetail(): LiveData<ShoesDetailEntity> = shoesDetailLiveData


    fun getShoesDetailByIDFromInternet(id:String) = viewModelScope.launch {

        val shoesDetail = repository.fetchShoesDetail(id)
        shoesDetail?.let {

            shoesDetailLiveData.postValue(it)
        }
    }


}