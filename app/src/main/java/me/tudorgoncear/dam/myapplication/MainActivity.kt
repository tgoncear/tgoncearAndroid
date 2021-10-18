package me.tudorgoncear.dam.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Inicialitzacio varables
    internal lateinit var buttonTocam : Button
    internal lateinit var nmTocam : TextView
    internal lateinit var tempsDisponible : TextView
    internal var counter = 0
    internal var temps = 60
    internal var started = false
    internal lateinit var timer : CountDownTimer
    internal val countDown : Long = 60000
    internal val intervalCountDown : Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCountDown();
        buttonTocam = findViewById(R.id.buttonTocam);
        nmTocam = findViewById(R.id.nmTocat);
        tempsDisponible = findViewById(R.id.tempsDisponible);

        buttonTocam.setOnClickListener{
            if(!started){
                startGame();
            }
            incrementar()

        }
        tempsDisponible.text = getString(R.string.tempsDisp, temps);
    }

    private fun startGame() {
        timer.start();
        started=true;
    }

    private fun initCountDown() {
        timer = object : CountDownTimer(countDown,intervalCountDown){
            override fun onTick(timeleft: Long) {
                var tempsRestant = timeleft / intervalCountDown;
                tempsDisponible.text = tempsRestant.toString();
            }

            override fun onFinish() {
                endGame();
            }

        }
    }

    //Metode per a poder incrementar el contador
    private fun incrementar() {
        counter += 1;
        nmTocam.text = counter.toString();
    }
    private fun endGame(){
        Toast.makeText(this,getString(R.string.endGame), Toast.LENGTH_LONG).show();
    }
}