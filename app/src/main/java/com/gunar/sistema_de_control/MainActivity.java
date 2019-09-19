package com.gunar.sistema_de_control;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {


String contenido = "", nombreCompleto = "", carnet="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                carnet = extras.getString("carnet");
                buscar(null);

            }
        }

    }


    public void buscar(View view){
        String estado = Environment.getExternalStorageState();

        contenido = "";
        nombreCompleto = "";

        if (!estado.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "No hay SD Card", Toast.LENGTH_LONG).show();
            finish();
        }

        try {
            EditText ci = (EditText) findViewById(R.id.carnet);
            String ciText = ci.getText().toString();

            if(!carnet.equals("")){
                ciText = carnet;
                ci.setText(ciText);

            }





//            Map<String, Float> mapa = new TreeMap<String, Float>();

            File dir = Environment.getExternalStorageDirectory();
            File pt = new File(dir.getAbsolutePath()+ File.separator + "bdPersonal.txt");

            BufferedReader lee = new BufferedReader(new FileReader(pt));
            String res = "", linea;

            nombreCompleto = "";

            while ((linea=lee.readLine())!=null){
                linea+=";";
                String[] separado = linea.split(";");
                String app = separado[0];
                String apm = separado[1];
                String nom = separado[2];
                String tel = separado[3];
                String carnet = separado[4];

                if(carnet.equals(ciText)){
                    EditText paterno = (EditText) findViewById(R.id.paterno);
                    EditText materno = (EditText) findViewById(R.id.materno);
                    EditText nombres = (EditText) findViewById(R.id.nombres);
                    EditText telefono = (EditText) findViewById(R.id.telefono);

                    nombreCompleto = app + " " + apm +" " + nom + " " + carnet;

                    paterno.setText(app);
                    materno.setText(apm);
                    nombres.setText(nom);
                    telefono.setText(tel);
                    break;
                }

            }


            File dir1 = Environment.getExternalStorageDirectory();
            File pt1 = new File(dir1.getAbsolutePath()+ File.separator + "bdAportes.txt");

            BufferedReader lee1 = new BufferedReader(new FileReader(pt1));
            String res1 = "", linea1;

            int cont = 0, total = 0;
            while ((linea1=lee1.readLine())!=null){
                linea1+=";";
                String[] separado = linea1.split(";");
                String carnet = separado[0];
                int aporte = Integer.parseInt(separado[1]);
                String cat = separado[2];


                if(carnet.equals(ciText)){

                    contenido+= aporte + " " + cat + "\n";
                    cont++;

                    total+=aporte;

                }

            }


            EditText aporte = (EditText) findViewById(R.id.aportes);
            aporte.setText(cont+"");

            EditText totalAportes = (EditText) findViewById(R.id.totalAportes);
            totalAportes.setText(total+"");


        }
        catch (Exception e){

        }

    }

    public void enviarDatos(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("nombreCompleto", nombreCompleto);
        intent.putExtra("contenido", contenido);
        startActivity(intent);
    }

    public void llevar(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("nombreCompleto", nombreCompleto);
        intent.putExtra("contenido", contenido);
        startActivity(intent);
    }
}
