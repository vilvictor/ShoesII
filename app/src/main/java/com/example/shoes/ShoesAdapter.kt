package com.example.shoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoes.remote.local.entity.ShoesEntity
import com.example.shoes.databinding.ShoesListBinding

class ShoesAdapter : RecyclerView.Adapter<ShoesAdapter.ShoesVH>() {

    private var listShoes = listOf<ShoesEntity>()
    private val SelectedShoes = MutableLiveData<ShoesEntity>()



    fun update(list:List<ShoesEntity>){
        listShoes= list
        notifyDataSetChanged()
    }


    // FUNCION PARA SELECCIONAR

    fun selectedShoes():
            LiveData<ShoesEntity> = SelectedShoes


    inner class  ShoesVH(private val mBinding: ShoesListBinding):
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{

        fun bind(shoes: ShoesEntity){
            Glide.with(mBinding.ivLogo).load(shoes.imageLink).centerCrop().into(mBinding.ivLogo)

            mBinding.tvname.text = shoes.name
            mBinding.tvdescription.text = shoes.origen

            itemView.setOnClickListener(this)

        }
        override  fun onClick(v: View){

            SelectedShoes.value= listShoes[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoesVH {
        return ShoesVH(ShoesListBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ShoesVH, position: Int) {
        val shoes = listShoes[position]
        holder.bind(shoes)
    }


    override fun getItemCount()=
        listShoes.size
}




