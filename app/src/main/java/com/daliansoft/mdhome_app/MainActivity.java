package com.daliansoft.mdhome_app;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.graphics.Color;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.app.PendingIntent;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,myRef2,myRef3;

    //Variable para redireccionar a la activity ventilador
    Button ventilador,clima,puerta;

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch led = (Switch)findViewById(R.id.led);
        Switch led2 = (Switch)findViewById(R.id.led2);
        Switch led3 = (Switch)findViewById(R.id.led3);

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("led");
        myRef2 = database.getReference("led2");
        myRef3 = database.getReference("led3");

        led.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    createNotificacion();
                    createNotificacionChannel();
                    myRef.setValue(1);
                }else{
                    myRef.setValue(0);
                }
            }
        });
        led2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b2) {
                if(b2){
                    createNotificacion();
                    createNotificacionChannel();
                    myRef2.setValue(1);
                }else{
                    myRef2.setValue(0);
                }
            }
        });
        led3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b3) {
                if(b3){
                    createNotificacion();
                    createNotificacionChannel();
                    myRef3.setValue(1);
                }else{
                    myRef3.setValue(0);
                }
            }
        });

        ventilador = (Button) findViewById(R.id.btnVentilador);
        clima = (Button) findViewById(R.id.btnClima);
        puerta = (Button)findViewById(R.id.btnPuerta);
        ventilador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventaVentilador = new Intent(MainActivity.this, VentiladorActivity.class);
                startActivity(ventaVentilador);
            }
        });

        clima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventnaClima = new Intent(MainActivity.this,ClimaActivity.class);
                startActivity(ventnaClima);
            }
        });
        puerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irPuerta = new Intent(MainActivity.this,PuertaActivity.class);
                startActivity(irPuerta);
            }
        });
    }
    private void createNotificacionChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Luces Encendidas, Trata de Ahorrar Energia";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    private void createNotificacion(){
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_opacity_black_24dp);
        builder.setContentTitle("Luces Encendidas");
        builder.setContentText("Ahorra energia y apaga las luces no necesarias");
        builder.setColor(Color.GREEN);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,100});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }
}
