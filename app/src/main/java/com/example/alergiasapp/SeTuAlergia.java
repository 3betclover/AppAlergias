package com.example.alergiasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class SeTuAlergia extends AppCompatActivity {

    private final ArrayList<String> alergiasSeleccionadas = new ArrayList<>();
    private LinearLayout llAlergiasSeleccionadas;
    private Spinner spinnerAlergias;
    private boolean isFirstSelection = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_tu_alergia);

        Button botonSeleccionarAlergia = findViewById(R.id.btnSeleccionarAlergias);
        Button botonContinuar = findViewById(R.id.btnContinuar);
        llAlergiasSeleccionadas = findViewById(R.id.llAlergiasSeleccionadas);
        spinnerAlergias = findViewById(R.id.spinnerAlergias);

        // Estas variables pueden ser locales ya que solo se usan en onCreate
        ArrayList<String> alergiasFullList = new ArrayList<>();
        alergiasFullList.add("Seleccionar alergia");
        alergiasFullList.add("Cereales con Gluten");
        alergiasFullList.add("Crustáceos y sus productos");
        alergiasFullList.add("Huevos y sus productos");
        alergiasFullList.add("Pescados y productos pesqueros");
        alergiasFullList.add("Maní, soya y sus productos");
        alergiasFullList.add("Leche y productos lácteos");
        alergiasFullList.add("Nueces y productos derivados");
        alergiasFullList.add("Sulfito en concentraciones > 10mg/Kg");

        // Configura el Spinner con un adaptador personalizado
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, alergiasFullList) {
            @Override
            public int getCount() {
                // Excluye la última opción ("Seleccionar alergia") de la lista desplegable
                return super.getCount() - 1;
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlergias.setAdapter(spinnerAdapter);

        // Muestra la última opción como predeterminada en la vista inicial
        spinnerAlergias.setSelection(alergiasFullList.size() - 1);

        // Configura el botón para desplegar el Spinner
        botonSeleccionarAlergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Muestra el Spinner cuando se hace clic en el botón
                spinnerAlergias.setVisibility(View.VISIBLE);
                spinnerAlergias.performClick();
            }
        });

        spinnerAlergias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstSelection) {
                    isFirstSelection = false;
                    return;
                }

                String alergiaSeleccionada = (String) parent.getItemAtPosition(position);
                if (!alergiasSeleccionadas.contains(alergiaSeleccionada) && !alergiaSeleccionada.equals("Seleccionar alergia")) {
                    alergiasSeleccionadas.add(alergiaSeleccionada);
                    actualizarListaDeAlergias();
                }
                // Oculta el Spinner después de seleccionar una alergia
                spinnerAlergias.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int randomActivity = random.nextInt(3);
                Intent intent;

                switch (randomActivity) {
                    case 0:
                        intent = new Intent(SeTuAlergia.this, BaseDatosNo.class);
                        break;
                    case 1:
                        intent = new Intent(SeTuAlergia.this, NoAlergia.class);
                        break;
                    case 2:
                        intent = new Intent(SeTuAlergia.this, SiAlergia.class);
                        break;
                    default:
                        intent = new Intent(SeTuAlergia.this, BaseDatosNo.class);
                        break;
                }

                startActivity(intent);
            }
        });
    }

    private void actualizarListaDeAlergias() {
        llAlergiasSeleccionadas.removeAllViews();
        for (String alergia : alergiasSeleccionadas) {
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textoAlergia = new TextView(this);
            textoAlergia.setText(alergia);
            textoAlergia.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1
            ));
            textoAlergia.setPadding(16, 0, 16, 0);

            Button botonEliminar = new Button(this);
            botonEliminar.setText("Eliminar");
            botonEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alergiasSeleccionadas.remove(alergia);
                    actualizarListaDeAlergias();
                }
            });

            itemLayout.addView(textoAlergia);
            itemLayout.addView(botonEliminar);

            llAlergiasSeleccionadas.addView(itemLayout);
        }
    }
}