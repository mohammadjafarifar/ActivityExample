package com.example.activityexample;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.orhanobut.hawk.Hawk;


public class FormRegister extends AppCompatActivity {

    EditText edtName,edtfamily,edtEmail,edtAge;
    String  emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ActionBarDrawerToggle toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        boolean Result=true;

        /*drawer hamberger Menu */
        drawerLayout=findViewById(R.id.drawer_layout);

        toolbar = new ActionBarDrawerToggle(FormRegister.this,drawerLayout,R.string.drawer_open,R.string.drawer_open);


        drawerLayout.addDrawerListener(toolbar);
        toolbar.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*drawer hamberger Menu */
        edtName = findViewById(R.id.edtName);
        edtfamily=findViewById(R.id.edtFamily);
        edtAge=findViewById(R.id.edtAge);
        edtEmail=findViewById(R.id.edtEmail);

        Button btnRegister=findViewById(R.id.btnRegister);

       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String Name=edtName.getText().toString().trim();
               String Family=edtfamily.getText().toString().trim();
               String Age=edtAge.getText().toString().trim();
               String Email=edtEmail.getText().toString().trim();
               if(Name.length()<3 ||Family.length()<3)
               {
                   Toast.makeText(FormRegister.this,"Minimum 3 character for Name-Family",Toast.LENGTH_LONG).show();

               }else if(!Email.matches(emailPattern ) && edtEmail.length()<=0){
                   Toast.makeText(FormRegister.this,"Invalid email address",Toast.LENGTH_LONG).show();

               }
               else if(Integer.parseInt(Age)<0 && Integer.parseInt(Age)>999){
                   Toast.makeText(FormRegister.this,"Age is Invalid",Toast.LENGTH_LONG).show();

               }
               else{

                   Intent Send= new Intent(FormRegister.this,ConfirmRegister.class);
                   Send.putExtra("Name", Name);
                   Send.putExtra("Family",Family);
                   Send.putExtra("Age",Age);
                   Send.putExtra("Email", Email);
                   startActivityForResult(Send, 200);

               }
           }
       });

        TextView txtrecycle=findViewById(R.id.txtrecycle);
        txtrecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FormRegister.this,RecycleList.class);
                startActivityForResult(intent,201);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toolbar.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == Activity.RESULT_OK) {
                String fullName = data.getStringExtra("FullName");
                String Age=data.getStringExtra("Age");
                String Email=data.getStringExtra("Email");

//                PreferenceManager.getDefaultSharedPreferences(FormRegister.this).edit().putString("fullName", fullName).apply();
//                PreferenceManager.getDefaultSharedPreferences(FormRegister.this).edit().putString("Age", Age).apply();
//                PreferenceManager.getDefaultSharedPreferences(FormRegister.this).edit().putString("Email", Email).apply();

                Hawk.init(FormRegister.this).build();
                Hawk.put("fullName",fullName);
                Hawk.put("Age",Age);
                Hawk.put("Email",Email);
                edtName.setText("");
                edtfamily.setText("");
                edtAge.setText("");
                edtEmail.setText("");
                Toast.makeText(FormRegister.this, "Save is Successful", Toast.LENGTH_SHORT).show();

            }
            else if(resultCode==Activity.RESULT_CANCELED)
            {

                edtName.setText("");
                edtfamily.setText("");
                edtAge.setText("");
                edtEmail.setText("");
                Toast.makeText(FormRegister.this, "Information DO not Save", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
