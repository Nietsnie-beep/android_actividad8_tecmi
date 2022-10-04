package com.example.myapplication8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    EditText phone, message;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.phone);
        message = findViewById(R.id.mensaje);
        send = findViewById(R.id.btn_message);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    EnviarSms();
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.SEND_SMS
                    }, 100);
                }
            }
        });
    }

    private void EnviarSms(){
        String phone_s = phone.getText().toString().trim();
        String message_s = phone.getText().toString().trim();

        if (!phone_s.equals("") && !message_s.equals("")){
            SmsManager smsManager = SmsManager.getDefault();
            Toast.makeText(getApplicationContext(), "Sms enviado", Toast.LENGTH_LONG).show();

            smsManager.sendTextMessage(phone_s,null,message_s,null,null);

        }else{
            Toast.makeText(getApplicationContext(), "Enter value First", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            EnviarSms();
        }else{
            Toast.makeText(getApplicationContext(), "permiso denegado", Toast.LENGTH_SHORT).show();
        }
    }

}