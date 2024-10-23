package com.example.alergiasapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private LinearLayout llProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        llProductos = findViewById(R.id.llProductos);
        Button btnGuardarProductos = findViewById(R.id.btnGuardarProductos);
        Button btnAgregarMasCampos = findViewById(R.id.btnAgregarMasCampos);

        btnGuardarProductos.setOnClickListener(view -> {
            // Aquí puedes manejar la lógica para guardar los productos en la base de datos
        });

        btnAgregarMasCampos.setOnClickListener(view -> agregarCamposProducto());

        // Agregar el primer conjunto de campos de producto
        agregarCamposProducto();
    }

    private void agregarCamposProducto() {
        View productoView = getLayoutInflater().inflate(R.layout.activity_item_producto, llProductos, false);
        llProductos.addView(productoView);
    }
}