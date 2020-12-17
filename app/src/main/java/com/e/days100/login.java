package com.e.days100;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
 int counter=3;
    private Button login;
    private EditText password;
    private EditText username;
    public static final String MyPREFERENCES="my prefs";
    public static final String USERNAME="nameKey";
    public static final String PASSWORD="passwordKey";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_password);
        login = (Button) findViewById(R.id.btn_login);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if (user.equals("admin")
                        && pass.equals("admin")){
                    Toast.makeText(getApplicationContext(),"redirecting.....",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(USERNAME,user);
                    editor.putString(PASSWORD,pass);
                    editor.commit();

                    Intent intent=new Intent(login.this,MainActivity.class);
                    startActivity(intent);




                }
                else {

                    Toast.makeText(getApplicationContext(),"error wrong credentials",Toast.LENGTH_SHORT).show();
                    counter --;
                    if(counter ==0){
                        login.setEnabled(false);
                    }

                }
            }
        });
    }
}
