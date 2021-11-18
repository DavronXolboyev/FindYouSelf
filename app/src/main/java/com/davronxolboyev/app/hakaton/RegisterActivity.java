package com.davronxolboyev.app.hakaton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    AppCompatButton button;
    TextInputLayout name;
    TextInputLayout surname;
    TextInputLayout mail;
    static String fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = findViewById(R.id.goBtn);
        name = findViewById(R.id.username);
        surname = findViewById(R.id.userSurname);
        mail = findViewById(R.id.userEmail);

        button.setOnClickListener(v -> {

            String userName = Objects.requireNonNull(name.getEditText()).getText().toString().trim();
            String userSurName = Objects.requireNonNull(surname.getEditText()).getText().toString().trim();
            String userEmail = Objects.requireNonNull(mail.getEditText()).getText().toString().trim();
            if (!userName.isEmpty()){
                if (!userSurName.isEmpty()){
                    if (!userEmail.isEmpty()){

                        if (Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){

                            fullname = userName + " " + userSurName;
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("name",userName);
                            intent.putExtra("surname",userSurName);
                            intent.putExtra("mail",userEmail);
                            startActivity(intent);
                            finish();

                            mail.setError(null);
                        }else {
                            mail.setError("Email not entered");
                        }
                    }else {
                        mail.setError("Enter your email");
                    }

                    surname.setError(null);
                }else {
                    surname.setError("Enter your surname");
                }
                name.setError(null);
            }else {
                name.setError("Enter your name");
            }
        });
    }
}