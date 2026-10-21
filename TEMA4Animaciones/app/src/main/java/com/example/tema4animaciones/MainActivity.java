package com.example.tema4animaciones;

import android.os.Bundle;
import android.graphics.Color;
import android .graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.CompoundButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.RatingBar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Estos son los elementos que sugerirán
        String[] opciones={"opcion1", "opcion2", "opcion3", "opcion4", "opcion5"};
        AutoCompleteTextView textoLeido=findViewById(R.id.miTexto);
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        textoLeido.setAdapter(adaptador);
    }

    protected void onStart(){
        super.onStart();
        Button boton1= findViewById(R.id.button1);
        Button boton2= findViewById(R.id.button2);
        Button boton3= findViewById(R.id.button3);
        CheckBox checkbox1=findViewById(R.id.checkbox1);
        Spinner miSpinner=findViewById(R.id.miSpinner);
        Switch switch1=findViewById(R.id.switch1);
        RatingBar ratingBar=findViewById(R.id.ratingBar);
        TextView textViewRating=findViewById(R.id.textViewRating);
        String[] valores= {"Star Wars", "Reservoir dogs", "El Club de la lucha", "Snach: Cerdos y Diamantes"};
        miSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        TextView miTexto=(TextView) findViewById(R.id.texto);
        TextView textViewStatus=(TextView) findViewById(R.id.textViewStatus);
        TextView textViewProgress=(TextView) findViewById(R.id.textViewProgress);
        SeekBar seekBar=(SeekBar) findViewById(R.id.seekBar);
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
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l){
               System.out.println("Has seleccionado el valor: "+adapterView.getItemAtPosition(position));
           }

           @Override
            public void onNothingSelected(AdapterView<?> adapterView){
               System.out.println("No has seleccionado nada");
           }
        });
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
             if (isChecked){
                //Código seleccionado
             }else{
                //Código no seleccionado
             }
         }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    textViewStatus.setText("Estado: Encendido");
                }else{
                    textViewStatus.setText("Estado: Apagado");
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                textViewProgress.setText("Valor: "+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                System.out.println("Se ha tocado la seekbar");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
                System.out.println("Se ha dejado de tocar la seekbar");
            }

        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textViewRating.setText("Calificación: "+rating);
            }
        });
    }
}

    /*public class Radio{
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            radioGroup=findViewById(R.id.radioGroup);
            textViewResult=findViewById(R.id.textViewResult);

            buttonCheck.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int selectedId=radioGroup.getCheckedRadioButtonId();

                    if(selectedId!=-1){
                        RadioButton selectedRadioButton = findViewById(selectedId);
                        String selectedOption=selectedRadioButton.getText().toString();
                        textViewResult.setText("Seleccionado: "+selectedOption);
                    }else{
                        textViewResult.setText("No se ha seleccionado ninguna opción");
                        }
                    }
                });
            }
        }
    }
     */