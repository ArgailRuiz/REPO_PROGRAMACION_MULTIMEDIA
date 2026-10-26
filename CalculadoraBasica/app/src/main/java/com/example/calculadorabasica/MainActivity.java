package com.example.calculadorabasica;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Inicialización de variables usadas en el código
    String numeros="";
    String numero1;
    String numero2;
    float n1;
    float n2;
    float resultado;

    String operacion;
    boolean ceroAlaIzquierda=true;

    boolean operacionIniciada=false;

    @Override
    //Se crea la aplicación
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    //Núcleo de la aplicación
    protected void onStart(){
        super.onStart();
        //Los componentes de la calculadora
        TextView txtViewNumeros=findViewById(R.id.txtViewNumeros);
        TextView txtViewOperacion=findViewById(R.id.txtViewOperacion);
        Button button0= findViewById(R.id.button0);
        Button button1= findViewById(R.id.button1);
        Button button2= findViewById(R.id.button2);
        Button button3= findViewById(R.id.button3);
        Button button4= findViewById(R.id.button4);
        Button button5= findViewById(R.id.button5);
        Button button6= findViewById(R.id.button6);
        Button button7= findViewById(R.id.button7);
        Button button8= findViewById(R.id.button8);
        Button button9= findViewById(R.id.button9);
        Button buttonSumar= findViewById(R.id.buttonSumar);
        Button buttonRestar= findViewById(R.id.buttonRestar);
        Button buttonMultiplicar= findViewById(R.id.buttonMultiplicar);
        Button buttonDividir= findViewById(R.id.buttonDividir);
        Button buttonComa=findViewById(R.id.buttonComa);
        Button buttonC=findViewById(R.id.buttonC);
        Button buttonAC=findViewById(R.id.buttonAC);
        Button buttonIgual=findViewById(R.id.buttonIgual);

        //No nos permite introducir un 0 como primer numero, pero pulsar solo la coma produce el mismo resultado
        button0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String cero="0";
                if (ceroAlaIzquierda){
                    return;
                }
                aniadirNumero(cero);
                txtViewNumeros.setText(numeros);
            }
        });
        //El resto de botones númericos son simples, señalan que ya podemos introducir cero y añaden el numero al String y el textview
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String uno="1";
                aniadirNumero(uno);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String dos="2";
                aniadirNumero(dos);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String tres="3";
                aniadirNumero(tres);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String cuatro="4";
                aniadirNumero(cuatro);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String cinco="5";
                aniadirNumero(cinco);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String seis="6";
                aniadirNumero(seis);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String siete="7";
                aniadirNumero(siete);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String ocho="8";
                aniadirNumero(ocho);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String nueve="9";
                aniadirNumero(nueve);
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=false;
            }
        });
        //la coma nos permite introducir cero o números inferiores, se autoregula comprobando que no haya más comas al pulsarlo
        buttonComa.setOnClickListener(new View.OnClickListener(){
            Character coma=',';
            public void onClick(View v){
                for (int i=0; i<numeros.length();i++){
                    if (numeros.charAt(i) == coma){
                        return;
                    }
                }
                if (ceroAlaIzquierda){
                    numeros="0,";
                    txtViewNumeros.setText(numeros);
                    ceroAlaIzquierda=false;
                }else{
                    aniadirNumero(",");
                    txtViewNumeros.setText(numeros);
                }

            }
        });
        //reinicia la calculadora por completo devolviendo todos los valores a su estado inicial
        buttonAC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numeros="";
                numero1="";
                operacion="";
                txtViewNumeros.setText(numeros);
                ceroAlaIzquierda=true;
                operacionIniciada=false;
            }
        });
        //borra el último caracter introducido dandole a numeros su longitud menos uno
        buttonC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!numeros.isEmpty()){
                    numeros=numeros.substring(0,numeros.length()-1);
                    txtViewNumeros.setText(numeros);
                }
                if(numeros.isEmpty()){
                    ceroAlaIzquierda=true;
                }
            }
        });

        //Los botones de operacion registran y eliminan el anterior numero introducido, inicializando el string operacion para el switch que ejecuta =
        buttonSumar.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
              if(!numeros.isEmpty()) {
                  numero1 = numeros;
                  operacion="+";
                  numeros = "";
                  txtViewNumeros.setText("");
                  txtViewOperacion.setText(numero1+operacion);
                  ceroAlaIzquierda=true;
                  operacionIniciada = true;
              }
           }
        });
        buttonRestar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!numeros.isEmpty()) {
                    numero1 = numeros;
                    operacion="-";
                    numeros = "";
                    txtViewNumeros.setText("");
                    txtViewOperacion.setText(numero1+operacion);
                    ceroAlaIzquierda=true;
                    operacionIniciada = true;
                }
            }
        });
        buttonMultiplicar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!numeros.isEmpty()) {
                    numero1 = numeros;
                    operacion="*";
                    numeros = "";
                    txtViewNumeros.setText("");
                    txtViewOperacion.setText(numero1+operacion);
                    ceroAlaIzquierda=true;
                    operacionIniciada = true;
                }
            }
        });
        buttonDividir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!numeros.isEmpty()) {
                    numero1 = numeros;
                    operacion="/";
                    numeros = "";
                    txtViewNumeros.setText("");
                    txtViewOperacion.setText(numero1+operacion);
                    ceroAlaIzquierda=true;
                    operacionIniciada = true;
                }
            }
        });

        //igual realiza diferentes operaciones dependiendo del botón de operación previamente seleccionado, solo funciona si se dan las condiciones
        buttonIgual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!numeros.isEmpty() && !numero1.isEmpty() && !operacion.isEmpty()){
                    numero2=numeros;

                    n1=Float.parseFloat(numero1.replace(',','.'));
                    n2=Float.parseFloat(numero2.replace(',', '.'));

                    switch(operacion){
                        case "+":
                            resultado = n1 + n2;
                            break;

                        case "-":
                            resultado = n1 - n2;
                            break;

                        case "*":
                            resultado = n1 * n2;
                            break;

                        case "/":
                            if (n2 == 0) {
                                txtViewNumeros.setText("Error");
                                numeros = "";
                                operacionIniciada = false;
                                return;
                            }
                            resultado = n1 / n2;
                            break;
                    }
                    if(resultado==(long) resultado){
                        numeros=String.valueOf((long) resultado);
                    }else{
                        numeros=String.valueOf(resultado).replace('.', ',');
                    }
                    txtViewNumeros.setText(numeros);

                    numero1= numeros;
                    operacion="";
                    operacionIniciada=false;
                    ceroAlaIzquierda=false;
                }
            }
        });

    }
    //Este metodo es como vamos añadiendo los numeros
    public void aniadirNumero(String numero){
        numeros= numeros+numero;
    }

    //Mejoras: Hacer que se pueda seleccionar cero por su cuenta
}