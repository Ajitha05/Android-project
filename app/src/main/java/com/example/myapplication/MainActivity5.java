package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity5 extends AppCompatActivity {

    private EditText femail;
    private Button btn;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        femail=findViewById(R.id.femail);
        btn = findViewById(R.id.resend);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                email = femail.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(MainActivity5.this, "please provide your email", Toast.LENGTH_SHORT).show();
                }else{
                    forgotpassword();
                }
            }

        });

    }

    private void forgotpassword() {

        FirebaseAuth Auth =FirebaseAuth.getInstance();

        Auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity5.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity5.this,MainActivity.class));
                            finish();

                        }else{
                            Toast.makeText(MainActivity5.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}