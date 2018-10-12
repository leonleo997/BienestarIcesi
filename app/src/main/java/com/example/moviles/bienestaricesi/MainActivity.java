package com.example.moviles.bienestaricesi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviles.bienestaricesi.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;
    FirebaseAuth auth;

    private EditText et_usr;
    private EditText et_email;
    private EditText et_name;
    private EditText et_pwd;
    private EditText et_con_pwd;

    private Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        DatabaseReference dbr = db.getReference().child("estudiantes");
        dbr.setValue("Yesid");


        loadComponents();

    }

    private void loadComponents() {
        et_con_pwd = findViewById(R.id.et_con_pwd);
        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_pwd = findViewById(R.id.et_pwd);
        et_usr = findViewById(R.id.et_usr);

        btn_registrar = findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario user = new Usuario("", et_usr.getText().toString(), et_email.getText().toString(), et_name.getText().toString());
                registrarUsuario(user);
            }
        });
    }

    private void registrarUsuario(final Usuario user) {
        if (et_pwd.getText().toString().length() <= 5) {
            Toast.makeText(this, "La contraseña debe ser de mínimo 6 o más caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!et_pwd.getText().toString().equals(et_con_pwd.getText().toString())) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(user.getCorreo(), et_pwd.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    user.setUid(auth.getCurrentUser().getUid());
                    Log.i("USUARIO", "onComplete: " + user.getNombre());
                    DatabaseReference dbr = db.getReference().child("Usuarios").child(user.getUid());
                    dbr.setValue(user);


                    //Aquí va para el perfil


                } else {
                    Toast.makeText(MainActivity.this, "Registro fallido: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
