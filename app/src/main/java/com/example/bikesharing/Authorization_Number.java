package com.example.bikesharing;

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
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Authorization_Number extends AppCompatActivity {

    private boolean otpSent = false;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_number);

        final EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        final EditText editTextCode = findViewById(R.id.editTextCode);
        final Button button_submit_number = findViewById(R.id.button_submit_number);

        FirebaseApp.initializeApp(this);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        button_submit_number.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".Authorization_Code");
                Toast.makeText(Authorization_Number.this, "ОДИН"+id, Toast.LENGTH_SHORT).show();
                    final String getMobile = editTextPhoneNumber.getText().toString();
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(getMobile)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(Authorization_Number.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    Toast.makeText(Authorization_Number.this, "Код получен", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(Authorization_Number.this, "ВТОРАЯ ОШИБКА"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);

                                    id = s;
                                    Toast.makeText(Authorization_Number.this, "ДВА "+id, Toast.LENGTH_SHORT).show();
                                    intent.putExtra("id", id);
                                    startActivity(intent);
                                    otpSent = true;
                                }
                            }).build();

                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
        }));

    }

}