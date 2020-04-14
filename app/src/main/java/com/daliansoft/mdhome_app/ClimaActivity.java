package com.daliansoft.mdhome_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClimaActivity extends AppCompatActivity {
    Button regresar;
    TextView showHumedad, showTemperatura;
    DatabaseReference hDatabase,tDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima);
        showHumedad = (TextView)findViewById(R.id.txtHumedad);
        showTemperatura = (TextView)findViewById(R.id.txtTemperatura);

        hDatabase = FirebaseDatabase.getInstance().getReference();
        tDatabase = FirebaseDatabase.getInstance().getReference();

        hDatabase.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    float humedad = Float.parseFloat(dataSnapshot.child("humidity").getValue().toString());
                    showHumedad.setText("En este instante la humedad es: "+humedad+" %");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tDatabase.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                if(dataSnapshot2.exists()){
                    float temperatura = Float.parseFloat(dataSnapshot2.child("temperature").getValue().toString());
                    showTemperatura.setText("En este instante la temperatura es: "+temperatura+" F°");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError2) {

            }
        });

        regresar = (Button) findViewById(R.id.btnRegresar) ;
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(ClimaActivity.this, MainActivity.class);
                startActivity(main);
            }
        });

    }//OnCreate
}//Clase
