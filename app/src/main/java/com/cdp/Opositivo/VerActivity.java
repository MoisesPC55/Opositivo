package com.cdp.Opositivo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cdp.Opositivo.db.DbOpositivo;
import com.cdp.Opositivo.entidades.Donante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText TextNombre, TextEmail, TextTelefono, TextDoc_identidad, TextTipo_sangre, TextDireccion;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Donante donantes;
    int id = 0;

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
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnRegistrar);
        btnGuarda.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbOpositivo dbOpositivo = new DbOpositivo(VerActivity.this);
        donantes = dbOpositivo.verDonante(id);

        if (donantes != null) {
            TextNombre.setText(donantes.getNombre());
            TextEmail.setText(donantes.getEmail());
            TextTelefono.setText(donantes.getTelefono());
            TextDoc_identidad.setText(donantes.getDoc_identidad());
            TextTipo_sangre.setText(donantes.getTipo_sangre());
            TextDireccion.setText(donantes.getDireccion());
            TextNombre.setInputType(InputType.TYPE_NULL);
            TextEmail.setInputType(InputType.TYPE_NULL);
            TextTelefono.setInputType(InputType.TYPE_NULL);
            TextDoc_identidad.setInputType(InputType.TYPE_NULL);
            TextTipo_sangre.setInputType(InputType.TYPE_NULL);
            TextDireccion.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbOpositivo.eliminarDonante(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}