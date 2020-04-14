package com.daliansoft.mdhome_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VentiladorActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference referencia;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilador);

        Switch ventilador = (Switch)findViewById(R.id.ventilador);

        database = FirebaseDatabase.getInstance();
        referencia = database.getReference("ventilador");

        ventilador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    referencia.setValue(1);
                }else{
                    referencia.setValue(0);
                }
            }
        });


        regresar = (Button)findViewById(R.id.btnRegresar2);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresa = new Intent(VentiladorActivity.this,MainActivity.class);
                startActivity(regresa);
            }
        });

    }
}
