package com.example.alergiasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText Nombre, Rut, Password;
    Button Aceptar, Volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        View vwRegistrar = findViewById(R.id.vwRegistrar);

        Nombre = findViewById(R.id.etNombre);
        Rut = findViewById(R.id.etRut);
        Password = findViewById(R.id.etPassword);
        Aceptar = findViewById(R.id.btnAceptar);
        Volver = findViewById(R.id.btnVolver);

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(getApplicationContext());
                SQLiteDatabase db = admin.getWritableDatabase();
                String nombre = Nombre.getText().toString();
                String rut = Rut.getText().toString();
                String password = Password.getText().toString();

                if (!nombre.isEmpty() && !rut.isEmpty() && !password.isEmpty()) {
                    ContentValues registro = new ContentValues();
                    registro.put("nombre", nombre);
                    registro.put("rut", rut);
                    registro.put("password", password);
                    db.insert("usuarios", null, registro);
                    db.close();
                    Nombre.setText("");
                    Rut.setText("");
                    Password.setText("");
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}