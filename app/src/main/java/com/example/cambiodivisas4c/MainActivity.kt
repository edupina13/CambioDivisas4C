package com.example.cambiodivisas4c

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.cambiodivisas4c.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var spinner1: Spinner
    lateinit var spinner2: Spinner
    var opcion1: Int = 0
    var opcion2: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spinner1 = binding.spinner1
        spinner2 = binding.spinner2
        val resultado = binding.resultado
        val cantidad = binding.cantidad.text

        val boton = binding.btnConvertir
        var dollar:Double = 20.07
        var euro:Double = 19.52
        var peso:Double = 1.00



        var total : Double = 0.0

        ArrayAdapter.createFromResource(
            this,
            R.array.tipoMoneda,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter
            spinner2.adapter = adapter
        }
        spinner1.onItemSelectedListener = this
        spinner2.onItemSelectedListener = this
        boton.setOnClickListener {
            if (cantidad.toString().isNotEmpty()){
                val cantidad2: Double = cantidad.toString().toDouble()
                when(opcion1){
                    0->{
                        when(opcion2){
                            0->{
                                total = cantidad2*peso

                            }
                            1->{
                                total = cantidad2/dollar

                            }
                            2->{
                                total = cantidad2 /euro

                            }
                        }
                    }
                    1->{
                        when(opcion2){
                            0->{
                                total = cantidad2 * dollar


                            }
                            1->{
                                total = (cantidad2*dollar)/dollar

                            }
                            2->{
                                total = (cantidad2*dollar)/euro

                            }
                        }

                    }
                    2->{
                        when(opcion2){
                            0->{
                                total = cantidad2 * euro


                            }
                            1->{
                                total = (cantidad2*euro) /dollar

                            }
                            2->{
                                total = (cantidad2*euro)/euro

                            }
                        }

                    }
                }
                resultado.text = String.format("El tipo de cambio es: %.2f",total )
            }
        }



    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0 != null) {
            if ( p0.id == spinner1.id) {
                opcion1 = p2
            } else if (p0.id == spinner2.id) {
                opcion2 = p2
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}