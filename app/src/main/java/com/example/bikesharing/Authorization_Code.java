package com.example.bikesharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class Authorization_Code extends AppCompatActivity {
    public boolean otpSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_code);

    }

    public void OnClickCode(View view) {
        final EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        final EditText editTextCode = findViewById(R.id.editTextCode);
        FirebaseApp.initializeApp(this);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final String getOTP = editTextCode.getText().toString();
        Bundle arguments = getIntent().getExtras();
        String id = getIntent().getStringExtra("id");
        Toast.makeText(Authorization_Code.this, "ТРИ"+id, Toast.LENGTH_SHORT).show();
        if (id.isEmpty()){
            Toast.makeText(Authorization_Code.this, "ЧЕТЫРЕ"+id, Toast.LENGTH_SHORT).show();
        }
        else {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, getOTP);
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful()){

                        FirebaseUser userDetails = task.getResult().getUser();
                        Toast.makeText(Authorization_Code.this, "Вы успешно вошли", Toast.LENGTH_SHORT).show(); //Перенести в Auth_Code.
                        Intent intent = new Intent(".MapsActivity");
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(Authorization_Code.this, "ТРЕТЬЯ ОШИБКА", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
    }
}