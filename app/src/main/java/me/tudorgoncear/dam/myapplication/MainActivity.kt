package me.tudorgoncear.dam.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    internal lateinit var buttonTocam : Button
    internal lateinit var nmTocam : TextView
    internal lateinit var tempsDisponible : TextView
    internal var counter = 0
    internal var temps = 60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTocam = findViewById(R.id.buttonTocam);
        nmTocam = findViewById(R.id.nmTocat);
        tempsDisponible = findViewById(R.id.tempsDisponible);

        buttonTocam.setOnClickListener{
            incrementar()
        }
        tempsDisponible.text = "Temps disponible : {temps}";
    }

    private fun incrementar() {
        counter += 1;
        nmTocam.text = counter.toString();
    }
}