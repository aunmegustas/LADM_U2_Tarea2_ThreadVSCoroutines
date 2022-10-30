package com.example.ladm_u2_tarea2_threadvscoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    var equiposFutbol = arrayOf("Barcelona","Arsenal","PSG","Chivas","Juventus","Manchester City")
    var mensajes = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var hilo = HiloSimple(this)

        hilo_btn.setOnClickListener {
            runOnUiThread{
                hilo.start()
            }
        }
        corr_btn.setOnClickListener {
            corrutinaSimple().start()
        }
        corr_runBlocking_btn.setOnClickListener {
            corrutinaRunBlocking()
        }
    }

    fun corrutinaSimple() = GlobalScope.launch {

        for (e in equiposFutbol) {
            runOnUiThread {
                corr_texto.text = e
            }
            delay(2000L)
        }

        corr_texto.text = "Fin de la corrutina"

    }

    fun corrutinaRunBlocking() = runBlocking {
        launch {
            for (e in equiposFutbol) {
                runOnUiThread {
                    corr_runBlocking_texto.text = e
                }
                delay(2000L)
            }
        }
        corr_runBlocking_texto.text = "Inicio de la corrutina."
    }
}

class HiloSimple(puntero : MainActivity) : Thread(){
    var p = puntero
    override fun run() {
        super.run()
        for (e in p.equiposFutbol){
            p.hilo_texto.text = e
            sleep(2000)
        }
        p.hilo_texto.text = "Fin del hilo"

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