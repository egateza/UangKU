package com.uangku.ega.uangku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        final TextView username = (TextView) findViewById(R.id.username);
        final TextView password = (TextView) findViewById(R.id.password);
        Button btn_lgn = (Button) findViewById(R.id.lgn);

        btn_lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("egateza") && password.getText().toString().equals("123")) {
                    Intent i = new Intent(Login.this, navigation.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(Login.this, "username atau password salah!", Toast.LENGTH_SHORT).show();

                }
            }
        });



//        getSupportActionBar().setTitle("Login");

    }
}
