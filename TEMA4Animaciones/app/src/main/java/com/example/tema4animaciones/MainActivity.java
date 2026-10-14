package com.example.tema4animaciones;

import android.os.Bundle;
import android.graphics.Color;
import android .graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    protected void onStart(){
        super.onStart();
        Button boton1= findViewById(R.id.button1);
        Button boton2= findViewById(R.id.button2);
        Button boton3= findViewById(R.id.button3);
        TextView miTexto=(TextView) findViewById(R.id.texto);
        miTexto.setText("Nuevo texto a mostrar");
        Animation miAnimacion= AnimationUtils.loadAnimation(this, R.anim.animacion);
        Animation animacionRotar=AnimationUtils.loadAnimation(this, R.anim.rotar);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miTexto.setText("Se ha pulsado el boton");
                miTexto.startAnimation(miAnimacion);
                miAnimacion.setRepeatMode(Animation.RESTART);
                miAnimacion.setRepeatCount(20);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miTexto.setText("Se ha pulsado el otro boton");
                miTexto.startAnimation(animacionRotar);
                animacionRotar.setRepeatMode(Animation.RESTART);
                animacionRotar.setRepeatCount(20);
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miTexto.setText("Pausa");
                miTexto.clearAnimation();
            }
        });
    }
}