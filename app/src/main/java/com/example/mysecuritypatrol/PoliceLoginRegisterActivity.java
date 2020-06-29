package com.example.mysecuritypatrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PoliceLoginRegisterActivity extends AppCompatActivity {


    private Button PoliceLoginButton;
    private Button PoliceRegisterButton;
    private TextView PoliceRegisterLink;
    private TextView PoliceStatus;
    private EditText EmailPolice;
    private  EditText PasswordPolice;
    private ProgressDialog loadingBar;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login_register);

        mAuth = FirebaseAuth.getInstance();


        PoliceLoginButton = (Button) findViewById(R.id.police_login_btn);
        PoliceRegisterButton = (Button) findViewById(R.id.police_register_btn);
        PoliceRegisterLink = (TextView) findViewById(R.id.police_register_link);
        PoliceStatus = (TextView) findViewById(R.id.police_status);
        EmailPolice = (EditText) findViewById(R.id.email_police);
        PasswordPolice = (EditText) findViewById(R.id.password_police);
        loadingBar = new ProgressDialog(this);



        PoliceRegisterButton.setVisibility(View.INVISIBLE);
        PoliceRegisterButton.setEnabled(false);

        PoliceRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoliceLoginButton.setVisibility(View.INVISIBLE);
                PoliceRegisterLink.setVisibility(View.INVISIBLE);
                PoliceStatus.setText("Register Student");

                PoliceRegisterButton.setVisibility(View.VISIBLE);
                PoliceRegisterButton.setEnabled(true);

            }
        });

        PoliceRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email = EmailPolice.getText().toString();
                 String password = PasswordPolice.getText().toString();

                 RegisterPolice(email, password);
            }
        });

        PoliceLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = EmailPolice.getText().toString();
                String password = PasswordPolice.getText().toString();

                SignInPolice(email, password);
            }
        });
    }

    private void SignInPolice(String email, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(PoliceLoginRegisterActivity.this, "Please Enter Your Email...", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(PoliceLoginRegisterActivity.this, "Please Enter Your Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Police Login");
            loadingBar.setMessage("Please wait while we check your credentials...");
            loadingBar.show();


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(PoliceLoginRegisterActivity.this, "Police Login Successful...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent policeIntent = new Intent(PoliceLoginRegisterActivity.this, PoliceMapActivity.class);
                                startActivity(policeIntent);
                            }

                            else
                            {
                                Toast.makeText(PoliceLoginRegisterActivity.this, "Login Unsuccessful, Please Try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }


    private void RegisterPolice(String email, String password) {
    if (TextUtils.isEmpty(email))
    {
        Toast.makeText(PoliceLoginRegisterActivity.this, "Please Enter Your Email...", Toast.LENGTH_SHORT).show();
    }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(PoliceLoginRegisterActivity.this, "Please Enter Your Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Police Registration");
            loadingBar.setMessage("Please wait while we register your data...");
            loadingBar.show();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(PoliceLoginRegisterActivity.this, "Police Registration Successful...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent policeIntent = new Intent(PoliceLoginRegisterActivity.this, PoliceMapActivity.class);
                                startActivity(policeIntent);
                            }

                            else
                            {
                                Toast.makeText(PoliceLoginRegisterActivity.this, "Registration Unsuccessful, Please Try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}
