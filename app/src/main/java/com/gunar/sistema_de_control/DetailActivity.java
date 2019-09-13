package com.gunar.sistema_de_control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView nombreCompleto = (TextView) findViewById(R.id.nombreCompleto);
        nombreCompleto.setText(getIntent().getExtras().getString("nombreCompleto"));

        EditText contenido = (EditText) findViewById(R.id.contenido);
        contenido.setText(getIntent().getExtras().getString("contenido"));

    }

    public void retornar(View view){
        finish();
    }
}
