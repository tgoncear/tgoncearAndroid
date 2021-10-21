package me.tudorgoncear.dam.myapplication

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    //Inicialitzacio varables
    internal lateinit var buttonTocam : Button
    internal lateinit var nmTocam : TextView
    internal lateinit var tempsDisponible : TextView
    internal var counter = 0
    internal var temps = 60
    internal var started = false
    internal lateinit var timer : CountDownTimer
    internal val countDown : Long = 10000
    internal val intervalCountDown : Long = 1000
    val displayMetrics = DisplayMetrics();



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        windowManager.defaultDisplay.getMetrics(displayMetrics)
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
        run();
    }

    private fun initCountDown() {
        timer = object : CountDownTimer(countDown,intervalCountDown){
            override fun onTick(timeleft: Long) {
                var tempsRestant = timeleft / intervalCountDown;
                tempsDisponible.text = tempsRestant.toString();
            }

            override fun onFinish() {
                endGame();
                resetGame();
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
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder){
            this.setTitle("PUNTUACIÓ");
            this.setMessage("Has fet : " + counter + " clicks");
            this.setPositiveButton("OK",null)
            this.show();
        }

    }
    private fun resetGame(){
        counter = 0;
        started = false;

    }
    private fun stawnRandomButton(){

    }
    private fun getWidthScreen(activity: Activity): Int{
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
    private fun getHeightScreen(activity: Activity): Int{
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
    private fun run(){
        Random random = new Random();
        buttonTocam.animate()
            .x()
    }
}

