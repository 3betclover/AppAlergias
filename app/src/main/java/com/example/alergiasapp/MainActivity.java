package com.example.alergiasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText NombreUsuario, Password;
    Button Ingresar, Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NombreUsuario = findViewById(R.id.etNombreUsuario);
        Password = findViewById(R.id.etPassword);
        Ingresar = findViewById(R.id.btnIngresar);
        Registro = findViewById(R.id.btnRegistro);

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSqliteOpenHelper admin = new AdminSqliteOpenHelper(getApplicationContext());
                SQLiteDatabase db = admin.getWritableDatabase();
                String nombreUsuario = NombreUsuario.getText().toString();
                String password = Password.getText().toString();

                if (!nombreUsuario.isEmpty() && !password.isEmpty()) {
                    Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE nombre=? AND password=?", new String[]{nombreUsuario, password});
                    if (cursor.moveToFirst()) {
                        Toast.makeText(getApplicationContext(), "Login exitoso", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(MainActivity.this, SeTuAlergia.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                    cursor.close();
                    db.close();
                } else {
                    Toast.makeText(getApplicationContext(), "Debe ingresar el nombre de usuario y la contraseña", Toast.LENGTH_LONG).show();
                }
            }
        });

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}