package com.example.shoes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.shoes.Viewmodel.ShoesViewModel
import com.example.shoes.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit  var  mBinding: FragmentSecondBinding
    private val  mViewModel : ShoesViewModel by activityViewModels()
    // va almacenar el id del curso
    private var shoesId : String? = null





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // válidar que esta llegando la variable
        arguments?.let { bundle ->

            shoesId = bundle.getString("shoesId")
            Log.d("seleccion2",shoesId.toString())

        }

        shoesId?.let { id ->
            mViewModel.getShoesDetailByIDFromInternet(id)
        }

        mViewModel.getShoesDetail().observe(viewLifecycleOwner, Observer {

            Log.d("seleccion3",shoesId.toString())
            var id= it.id
            var name = it.name

            Glide.with(mBinding.ivLogo).load(it.imageLink).into(mBinding.ivLogo)
            mBinding.tvTitle.text= it.name
            mBinding.tvDescription.text= it.origen
            mBinding.tvMinimumSkill.text="entrega: ${it.entrega}"
            mBinding.tvModality.text="precio: ${it.precio}"
            mBinding.tvTuition.text="numero: ${it.numero}"



            // correo electronico


            mBinding.btMail.setOnClickListener{


                val mintent= Intent(Intent.ACTION_SEND)
                mintent.data= Uri.parse("mailto")
                mintent.type="text/plain"

                mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("admisión@centrofuturo.cl"))
                mintent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Solicito información sobre este curso"+ id
                )


                mintent.putExtra(

                    Intent.EXTRA_TEXT,"Hola\n"+

                            "Quisiera pedir información sobre este curso " + name + " ,\n" +
                            "me gustaría que me contactaran a este correo o al siguiente número\n" +
                            " _________\n" +
                            "Quedo atento."



                )
                try {
                    startActivity(mintent)
                } catch (e: Exception) {

                    Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
                }
            }})}

    override fun onDestroyView() {
        super.onDestroyView()
        // _binding = null
    }
}