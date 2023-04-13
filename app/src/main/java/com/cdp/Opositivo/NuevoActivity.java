package com.cdp.Opositivo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdp.Opositivo.db.DbOpositivo;

public class NuevoActivity extends AppCompatActivity {

    EditText TextNombre, TextEmail, TextTelefono, TextDoc_identidad, TextTipo_sangre, TextDireccion, TextContrasena;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        TextNombre = findViewById(R.id.TextNombre);
        TextEmail = findViewById(R.id.TextEmail);
        TextTelefono = findViewById(R.id.TextTelefono);
        TextDoc_identidad = findViewById(R.id.TextDoc_identidad);
        TextTipo_sangre = findViewById(R.id.TextTipo_sangre);
        TextDireccion = findViewById(R.id.TextDireccion);
        TextContrasena = findViewById(R.id.TextContrasena);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextNombre.getText().toString().equals("") && !TextEmail.getText().toString().equals("")) {
                    DbOpositivo dbOpositivo = new DbOpositivo(NuevoActivity.this);
                    long id = dbOpositivo.insertarDonante(TextNombre.getText().toString(), TextEmail.getText().toString(), TextTelefono.getText().toString(), TextDoc_identidad.getText().toString(), TextTipo_sangre.getText().toString(), TextDireccion.getText().toString(), TextContrasena.getText().toString());
                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        TextNombre.setText("");
        TextEmail.setText("");
        TextTelefono.setText("");
        TextDoc_identidad.setText("");
        TextTipo_sangre.setText("");
        TextDireccion.setText("");
        TextContrasena.setText("");
    }
}
