package com.example.rehber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rehber.Model.Address;
import com.example.rehber.Model.Model;


public class MainActivity extends AppCompatActivity {
    Button kaydet;
    EditText ad,ePosta,sehir,web;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ad = findViewById(R.id.ad_giris);
        ePosta = findViewById(R.id.email_giris);
        sehir = findViewById(R.id.sehir_giris);
        web = findViewById(R.id.web_giris);
        kaydet = findViewById(R.id.mainkaydetbutton);

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ad.getText().toString();
                String userEmail = ePosta.getText().toString();
                String userCity = sehir.getText().toString();
                String userWeb = web.getText().toString();
                Address address = new Address("sdsdsd","dfdfd","sehir.getText().toString()","dede");
                System.out.println("main"+address.getCity());
                Model model = new Model(username,userEmail,address,userWeb);
                Intent intent = new Intent(MainActivity.this,RehberActivity.class);


                /*intent.putExtra("username",username);
                intent.putExtra("userEmail",userEmail);
                intent.putExtra("userCity",userCity);
                intent.putExtra("userWeb",userWeb);
                intent.putExtra("key",1);*/
                intent.putExtra("model",model);
                intent.putExtra("adres",address);
                startActivity(intent);
            }
        });


    }

}