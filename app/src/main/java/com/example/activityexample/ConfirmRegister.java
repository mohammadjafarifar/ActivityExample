package com.example.activityexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmRegister extends AppCompatActivity {

    TextView txtFullName,txtAge,txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register);

        txtFullName=findViewById(R.id.txtfullname);
        txtAge=findViewById(R.id.txtAge);
        txtEmail=findViewById(R.id.txtEmail);

        Button btnCancel=findViewById(R.id.btnCancel);
        Button btnConfirm=findViewById(R.id.btnConfirm);

        Intent Recieve= getIntent();
        final String FullName=Recieve.getStringExtra("Name")+ " "+ Recieve.getStringExtra("Family");
        final String Email=Recieve.getStringExtra("Email");
        final String Age=Recieve.getStringExtra("Age");
        txtFullName.setText(FullName);
        txtEmail.setText(Email);
        txtAge.setText(Age);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send =new Intent();
                send.putExtra("FullName",FullName);
                send.putExtra("Age",Age);
                send.putExtra("Email",Email);
                setResult(Activity.RESULT_OK,send);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send =new Intent();
                setResult(Activity.RESULT_CANCELED,send);
                finish();
            }
        });


    }
}
