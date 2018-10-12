package com.example.moviles.bienestaricesi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    FirebaseDatabase db;
    FirebaseAuth auth;

    private EditText et_email;
    private EditText et_pwd;

    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(et_email.getText().toString(), et_pwd.getText().toString());
            }
        });

    }

    private void login(String user, String password) {
        auth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this, "Inicio de sesión exitoso: ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "Inicio de sesión fallido"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
