package com.example.mysecuritypatrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentLoginRegisterActivity extends AppCompatActivity {

    private Button StudentLoginButton;
    private Button StudentRegisterButton;
    private TextView StudentRegisterLink;
    private TextView StudentStatus;
    private EditText EmailStudent;
    private  EditText PasswordStudent;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_register);

        mAuth = FirebaseAuth.getInstance();


        StudentLoginButton = (Button) findViewById(R.id.student_login_btn);
        StudentRegisterButton = (Button) findViewById(R.id.student_register_btn);
        StudentRegisterLink = (TextView) findViewById(R.id.register_student_link);
        StudentStatus = (TextView) findViewById(R.id.student_status);
        EmailStudent = (EditText) findViewById(R.id.email_student);
        PasswordStudent = (EditText) findViewById(R.id.password_student);
        loadingBar = new ProgressDialog(this);


        StudentRegisterButton.setVisibility(View.INVISIBLE);
        StudentRegisterButton.setEnabled(false);

        StudentRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentLoginButton.setVisibility(View.INVISIBLE);
                StudentRegisterLink.setVisibility(View.INVISIBLE);
                StudentStatus.setText("Register Student");

                StudentRegisterButton.setVisibility(View.VISIBLE);
                StudentRegisterButton.setEnabled(true);

            }
        });

        StudentRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailStudent.getText().toString();
                String password = PasswordStudent.getText().toString();

                RegisterStudent(email, password);
            }
        });
        StudentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = EmailStudent.getText().toString();
                String password = PasswordStudent.getText().toString();

                SignInStudent(email, password);
                        
            }
        });
    }

    private void SignInStudent(String email, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(StudentLoginRegisterActivity.this, "Please Enter Your Email...", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(StudentLoginRegisterActivity.this, "Please Enter Your Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Student Login");
            loadingBar.setMessage("Please wait while we Log you in...");
            loadingBar.show();


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(StudentLoginRegisterActivity.this, "Student Login Successful...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                            else
                            {
                                Toast.makeText(StudentLoginRegisterActivity.this, "Login Unsuccessful, Please Try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void RegisterStudent(String email, String password)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(StudentLoginRegisterActivity.this, "Please Enter Your Email...", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(StudentLoginRegisterActivity.this, "Please Enter Your Password...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Student Registration");
            loadingBar.setMessage("Please wait while we register your data...");
            loadingBar.show();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(StudentLoginRegisterActivity.this, "Student Registration Successful...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                            else
                            {
                                Toast.makeText(StudentLoginRegisterActivity.this, "Registration Unsuccessful, Please Try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}
