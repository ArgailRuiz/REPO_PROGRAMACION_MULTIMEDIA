
package com.example.tema5adaptadorescustomizados;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Datos[] datos = new Datos[]{
                new Datos("Línea principal 1", "Línea inferior 1"),
                new Datos("Línea principal 2", "Línea inferior 2"),
                new Datos("Línea principal 3", "Línea inferior 3"),
                new Datos("Línea principal 4", "Línea inferior 4"),
        };

        ListView listado = findViewById(R.id.listView);
        Adaptador miAdaptador = new Adaptador(this, datos);
        View miCabecera= getLayoutInflater().inflate(R.layout.cabecera, null);
        listado.addHeaderView(miCabecera);
        listado.setAdapter(miAdaptador);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=((Datos)parent.getItemAtPosition(position)).getTexto1();
                Toast.makeText(MainActivity.this, "Elemento pulsado: "+selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class Datos {
        private String texto1;
        private String texto2;

        public Datos(String text1, String text2) {
            texto1 = text1;
            texto2 = text2;
        }

        public String getTexto1() {
            return texto1;
        }

        public String getTexto2() {
            return texto2;
        }
    }

    public class Adaptador extends ArrayAdapter<Datos> {
        private Datos[] datos;

        public Adaptador(Context context, Datos[] datos) {
            super(context, R.layout.elemento, datos);
            this.datos = datos;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mostrado = LayoutInflater.from(getContext());

            View elemento = mostrado.inflate(R.layout.elemento, parent, false);
            TextView text1 = elemento.findViewById(R.id.text1);
            TextView text2 = elemento.findViewById(R.id.text2);

            text1.setText(datos[position].getTexto1());
            text2.setText(datos[position].getTexto2());

            return elemento;
        }
    }
}
