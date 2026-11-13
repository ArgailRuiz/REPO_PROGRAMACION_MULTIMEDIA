package com.example.tema5gridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.GridView;
import java.util.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        List<String> data= Arrays.asList("España","Francia","Italia","Grecia","Alemania", "Polonia", "Irlanda", "Dinamarca", "Austria", "Paises Bajos");
        GridView listado= findViewById(R.id.gridView);
        TextView textView=findViewById(R.id.textView);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listado.setAdapter(adapter);

        listado.setAdapter(adapter);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long l){
                String elemento=(String) parent.getItemAtPosition(posicion);
                textView.setText(elemento);
            }
        });
    }
}