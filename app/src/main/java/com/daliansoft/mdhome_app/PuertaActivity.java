package com.daliansoft.mdhome_app;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import me.tankery.lib.circularseekbar.CircularSeekBar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PuertaActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button regresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puerta);

        Window win = getWindow();
        win.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("puerta");

        CircularSeekBar seekBar = (CircularSeekBar) findViewById(R.id.servoPuerta);
        seekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {

                int data= (int) progress;

                myRef.setValue(data);
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });

        regresa =(Button) findViewById(R.id.btnRegresarVen);
        regresa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Intent atras = new Intent(PuertaActivity.this,MainActivity.class);
                startActivity(atras);
            }
        });
    }
}
