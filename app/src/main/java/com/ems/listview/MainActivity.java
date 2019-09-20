package com.ems.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView tvEntrada;
    Button inserir;
    ListView lvLista;
    ArrayList<String> dados = new ArrayList<>();   // *new* //
    Button ordenar;
    Button ordenar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLista = findViewById(R.id.lista);
        tvEntrada = findViewById(R.id.entrada);
        inserir = findViewById(R.id.btinserir);
        ordenar = findViewById(R.id.btOrdem);
        ordenar2 = findViewById(R.id.btOrdem2);


        dados.add(0,"Placeholder pos 0");  // *new* //
        dados.add("Placeholder 2");
        dados.add("Placeholder 3");

        final ArrayAdapter<String> adaptador = new ArrayAdapter<>(  // adapta o array à ListView //
                getApplicationContext(),
                android.R.layout.simple_list_item_1, dados);
        lvLista.setAdapter(adaptador);

        inserir.setOnClickListener(new View.OnClickListener() {  // este botão envia o que foi digitado para o ListView //
            @Override
            public void onClick(View view) {
            dados.add(tvEntrada.getText().toString());
            adaptador.notifyDataSetChanged();
            tvEntrada.setText("");   // Limpa campo texto //
            tvEntrada.requestFocus(); // Volta ao campo tvEntrada //
            }
        });

        // interagir com itens da lista //
            lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {  // int i = posição do elemento na lista //
                    String texto = (String) lvLista.getItemAtPosition(posicao);
                    Toast.makeText(MainActivity.this, texto, Toast.LENGTH_SHORT).show(); // *new* //

                }
            });

            lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {

                    String texto = (String) lvLista.getItemAtPosition(posicao);

                    dados.remove(posicao);
                    Toast.makeText(MainActivity.this, ("Removido: " + texto), Toast.LENGTH_SHORT).show(); // *new* //
                    adaptador.notifyDataSetChanged();

                    return false;
                }
            });

            ordenar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.sort(dados);
                    adaptador.notifyDataSetChanged();

                }
            });

            ordenar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Collections.reverse(dados);
                    adaptador.notifyDataSetChanged();
                }
            });
    }
}
