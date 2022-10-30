package com.example.ladm_u2_tarea2_threadvscoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var equiposFutbol = arrayOf("Barcelona","Arsenal","PSG","Chivas","Juventus","Manchester City")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        hilo_btn.setOnClickListener {
            try{
                HiloBasico(this).start()
            }catch (e: Exception){}
        }
        corr_btn.setOnClickListener {
            CorrutinaBasica(equiposFutbol)
        }

    }

    fun CorrutinaBasica (lista : Array<String>) = GlobalScope.launch {
        for(l in lista){
            runOnUiThread {
                corr_texto.text = corr_texto.text.toString() + "\n $l"
            }
        }
        corr_texto.text = corr_texto.text.toString()+"\n FIN DE LA LISTA"
    }

}

class HiloBasico (p : MainActivity) : Thread() {
    var p = p

    override fun run() {
        super.run()
        for(l in p.equiposFutbol){
            p.hilo_texto.text = p.hilo_texto.text.toString()+"\n $l"
        }
        p.hilo_texto.text = p.hilo_texto.text.toString()+"\n FIN DE LA LISTA"
    }
}