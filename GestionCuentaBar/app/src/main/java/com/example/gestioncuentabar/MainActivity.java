package com.example.gestioncuentabar;

import android.os.Bundle;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Elementos de la app
        EditText editTextCuenta = findViewById(R.id.editTextCuenta);
        CheckBox checkBoxPropina = findViewById(R.id.checkBoxPropina);
        SeekBar seekBarPorcentajePropina = findViewById(R.id.seekBarPorcentajePropina);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Button botonTotal = findViewById(R.id.botonTotal);
        TextView textViewResultado = findViewById(R.id.textViewResultado);
        TextView textViewPorcentaje = findViewById(R.id.textViewPorcentaje);
        RadioGroup radioGroupPago = findViewById(R.id.radioGroupPago);

        // Estado inicial barra porcentaje propina
        seekBarPorcentajePropina.setProgress(10);
        checkBoxPropina.setOnCheckedChangeListener((buttonView, isChecked) -> {

            //Ocultar barra de propina si no esta seleccionada
            if (isChecked) {
                seekBarPorcentajePropina.setVisibility(View.VISIBLE);
                textViewPorcentaje.setVisibility(View.VISIBLE);
            } else {
                seekBarPorcentajePropina.setVisibility(View.GONE);
                textViewPorcentaje.setVisibility(View.GONE);
            }

        });
        // Actualizar el porcentaje cuando movemos el SeekBar
        seekBarPorcentajePropina.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        textViewPorcentaje.setText(getString(R.string.porcentajePropina, progress));
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        // Metodo del botón total
        botonTotal.setOnClickListener(v -> {

            String cuentaTexto = editTextCuenta.getText().toString().trim();

            // Comprobar si el campo está vacío
            if (cuentaTexto.isEmpty()) {
                mostrarError(textViewResultado, R.string.errorVacio);
                return;
            }
            double cuenta;
            // Comprobar si el valor introducido es numérico
            try {
                cuenta = Double.parseDouble(cuentaTexto.replace(',', '.'));
            } catch (NumberFormatException e) {
                mostrarError(textViewResultado, R.string.errorNumero);
                return;
            }
            // Comprobar que la cuenta sea mayor que cero
            if (cuenta <= 0 || Double.isNaN(cuenta) || Double.isInfinite(cuenta)) {
                mostrarError(textViewResultado, R.string.errorValor);
                return;
            }
            // Calcular la propina
            int porcentaje = seekBarPorcentajePropina.getProgress();
            double propina = 0;
            if (checkBoxPropina.isChecked()) {
                propina = cuenta * porcentaje / 100;
            }
            // Calcular el total
            double total = cuenta + propina;
            // Obtener el metodo de pago
            String metodoPago;
            if (radioGroupPago.getCheckedRadioButtonId() == R.id.radioButtonEfectivo) {
                metodoPago = getString(R.string.efectivo);
            } else {
                metodoPago = getString(R.string.tarjeta);
            }
            // Obtener la calificación del servicio
            float calificacion = ratingBar.getRating();
            // Formatear los importes con dos decimales
            String cuentaFormato = String.format(Locale.getDefault(), "%.2f", cuenta);
            String propinaFormato = String.format(Locale.getDefault(), "%.2f", propina);
            String totalFormato = String.format(Locale.getDefault(), "%.2f", total);
            // Mostrar el resultado
            String resultado = getString(
                    R.string.resultado,
                    cuentaFormato,
                    propinaFormato,
                    totalFormato,
                    metodoPago,
                    calificacion
            );
            textViewResultado.setTextColor(Color.BLACK);
            textViewResultado.setText(resultado);
        });
    }
    // Metodo para mostrar los mensajes de error
    private void mostrarError(TextView textView, int mensaje) {
        textView.setTextColor(Color.RED);
        textView.setText(getString(mensaje));
    }
}