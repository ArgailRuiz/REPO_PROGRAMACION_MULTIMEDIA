package com.example.tema4componentesdepantalla;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        TextView miTexto=(TextView) findViewById(R.id.texto);
        miTexto.setText("Nuevo texto a mostrar");
        //Opción 1 para cambiar el color.
        miTexto.setTextColor(Color.parseColor("#0000FF"));
        //Opción 2 para cambiar el color.
        miTexto.setTextColor(Color.RED);
        //Cambiar el texto a negrita
        miTexto.setTypeface(null, Typeface.ITALIC);
        //Cambiar tamaño texto
        miTexto.setTextSize(24);
        //Cambiar tipo de letra
        miTexto.setTypeface(Typeface.SANS_SERIF);
        //instanciamos y cargamos la animacion
        Animation miAnimacion=AnimationUtils.loadAnimation(this, R.anim.animacion);
        //aquí configuramos la animación
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);
        //Por ultimo se aplica la animacion al texto
        miTexto.startAnimation(miAnimacion);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miTexto.setText("Se ha pulsado el boton");
            }
        });
    }
}