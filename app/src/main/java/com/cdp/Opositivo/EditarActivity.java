package com.cdp.Opositivo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.Opositivo.db.DbOpositivo;
import com.cdp.Opositivo.entidades.Donante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText TextNombre, TextEmail, TextTelefono, TextDoc_identidad, TextTipo_sangre, TextDireccion;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Donante donantes;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        TextNombre = findViewById(R.id.TextNombre);
        TextEmail = findViewById(R.id.TextEmail);
        TextTelefono = findViewById(R.id.TextTelefono);
        TextDoc_identidad = findViewById(R.id.TextDoc_identidad);
        TextTipo_sangre = findViewById(R.id.TextTipo_sangre);
        TextDireccion = findViewById(R.id.TextDireccion);
        btnGuarda = findViewById(R.id.btnRegistrar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbOpositivo dbOpositivo = new DbOpositivo(EditarActivity.this);
        donantes = dbOpositivo.verDonante(id);

        if (donantes != null) {
            TextNombre.setText(donantes.getNombre());
            TextEmail.setText(donantes.getEmail());
            TextTelefono.setText(donantes.getTelefono());
            TextDoc_identidad.setText(donantes.getDoc_identidad());
            TextTipo_sangre.setText(donantes.getTipo_sangre());
            TextDireccion.setText(donantes.getDireccion());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextNombre.getText().toString().equals("") && !TextEmail.getText().toString().equals("")) {
                    correcto = dbOpositivo.editarDonante(id, TextNombre.getText().toString(), TextEmail.getText().toString(), TextTelefono.getText().toString(), TextDoc_identidad.getText().toString(), TextTipo_sangre.getText().toString(), TextDireccion.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}