package me.tudorgoncear.dam.myapplication

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.view.animation.AnimationUtils
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
    internal lateinit var timerMovimentBotto : CountDownTimer
    internal val countDown : Long = 60000
    internal val intervalCountDown : Long = 1000
    val displayMetrics = DisplayMetrics();
    var time = 0;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCountDown();
        buttonTocam = findViewById(R.id.buttonTocam);
        nmTocam = findViewById(R.id.nmTocat);
        tempsDisponible = findViewById(R.id.tempsDisponible);

        buttonTocam.setOnClickListener{ view ->
            val bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            view.startAnimation(bounceAnimation);
            if(!started){
                startGame();
            }
            run();
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
                time = tempsRestant.toInt();
                run();
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
        var dx = getWidthScreen(this) / 2;
        var dy = getHeightScreen(this) / 2;
        buttonTocam.animate()
            .x(dx.toFloat())
            .y(dy.toFloat())
            .setDuration(0)
            .start()
    }
    private fun stawnRandomButton(){

    }
    //Aquest metode per ara no es del tot funcional ja que la idea es que la agafi la mesura de la pantalla
    //pero es veu que no vol funcionar correctament
    private fun getWidthScreen(activity: MainActivity): Int{
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics);
        println("El width de la pantalla es: " + displayMetrics.widthPixels);
        return 1000;
    }
    private fun getHeightScreen(activity: MainActivity): Int{
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics);
        println("El height de la pantalla es: " + displayMetrics.heightPixels);
        return 2000;
    }
    private fun run(){
        val random = Random();
        val dx = random.nextFloat() * getWidthScreen(this);
        val dy = random.nextFloat() * getHeightScreen(this);
        buttonTocam.animate()
            .x(dx)
            .y(dy)
            .setDuration(0)
            .start();
        println(dx);
        println(dy);
    }
}

