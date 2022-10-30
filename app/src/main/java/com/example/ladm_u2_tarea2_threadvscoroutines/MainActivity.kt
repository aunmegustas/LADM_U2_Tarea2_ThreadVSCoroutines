package com.example.ladm_u2_tarea2_threadvscoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ladm_u2_tarea2_threadvscoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var equiposFutbol = arrayOf("Barcelona","Arsenal","PSG","Chivas","Juventus","Manchester City")
    var h = HiloBasico(this,equiposFutbol)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hiloBtn.setOnClickListener {
            h.start()
        }


    }
}

class HiloBasico (p : MainActivity, lista : Array<String>) : Thread() {
    var p = p
    var lista = lista
    override fun run() {
        super.run()
        for(l in lista){
            p.binding.hiloTexto.text = p.binding.hiloTexto.text.toString()+"\n"+l
        }
        p.binding.hiloTexto.text = p.binding.hiloTexto.text.toString()+"\n FIN DE LA LISTA"
    }
}