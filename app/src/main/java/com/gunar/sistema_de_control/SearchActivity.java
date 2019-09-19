package com.gunar.sistema_de_control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gunar.sistema_de_control.adapter.PersonaAdapter;
import com.gunar.sistema_de_control.model.Persona;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<Persona> comenList = new ArrayList<>();

    private List<Persona> comenListAux = new ArrayList<>();


    private RecyclerView comenRecycler;
    private PersonaAdapter comenAdapter;
    private EditText busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        busqueda = (EditText) findViewById(R.id.busqueda);

        comenRecycler = (RecyclerView) findViewById(R.id.personaRecycler);

        comenAdapter = new PersonaAdapter(comenListAux);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        comenRecycler.setLayoutManager(mLayoutManager);
        comenRecycler.setItemAnimator(new DefaultItemAnimator());
        comenRecycler.setAdapter(comenAdapter);
        comenRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        comenAdapter.setOnItemClickListener(new PersonaAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("carnet", comenListAux.get(0).getCarnet());
                startActivity(intent);

            }

        });

        mostrar();



    }


    public void mostrar() {
        String estado = Environment.getExternalStorageState();

//        contenido = "";
//        nombreCompleto = "";

        if (!estado.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "No hay SD Card", Toast.LENGTH_LONG).show();
            finish();
        }

        try {


            File dir = Environment.getExternalStorageDirectory();
            File pt = new File(dir.getAbsolutePath()+ File.separator + "bdPersonal.txt");

            BufferedReader lee = new BufferedReader(new FileReader(pt));
            String res = "", linea;

//            nombreCompleto = "";

            while ((linea=lee.readLine())!=null){
                linea+=";";
                String[] separado = linea.split(";");
                String app = separado[0];
                String apm = separado[1];
                String nom = separado[2];
                String tel = separado[3];
                String carnet = separado[4];

                comenList.add(new Persona(nom, carnet, tel));

            }

            comenAdapter.notifyDataSetChanged();



        }
        catch (Exception e){

        }
    }



    public void buscar(View view){
        String busq = busqueda.getText().toString();
        comenListAux.clear();

        if(!busq.equals("")){
            for (int i=0; i<comenList.size(); i++){
                if(comenList.get(i).getNombre().toLowerCase().contains(busq.toLowerCase())){
                    comenListAux.add(comenList.get(i));
                }
            }
        }



        comenAdapter.notifyDataSetChanged();

    }


    public void retornar(View view){
        finish();
    }

    public void reset(View view){
        comenListAux.clear();
        comenAdapter.notifyDataSetChanged();
        busqueda.setText("");

    }
}
