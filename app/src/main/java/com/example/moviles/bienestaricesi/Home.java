package com.example.moviles.bienestaricesi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText et_comentario;
    private Button btn_comentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            Toast.makeText(this.getApplicationContext(), "Failed to connect", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Home.this, Login.class);
            startActivity(i);
            finish();
            return;
        }
        et_comentario=findViewById(R.id.et_comentario);

        btn_comentar=findViewById(R.id.btn_comentar);

        btn_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
