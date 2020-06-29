package com.example.mysecuritypatrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button WelcomePoliceButton;
    private Button WelcomeStudentButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        WelcomeStudentButton = (Button) findViewById(R.id.welcome_student_btn);
        WelcomePoliceButton = (Button) findViewById(R.id.welcome_police_btn);

        WelcomeStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginRegisterStudentIntent = new Intent(WelcomeActivity.this, StudentLoginRegisterActivity.class);
                startActivity(LoginRegisterStudentIntent);
            }
        });

        WelcomePoliceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginRegisterStudentIntent = new Intent(WelcomeActivity.this, PoliceLoginRegisterActivity.class);
                startActivity(LoginRegisterStudentIntent);
            }
        });
    }
}
