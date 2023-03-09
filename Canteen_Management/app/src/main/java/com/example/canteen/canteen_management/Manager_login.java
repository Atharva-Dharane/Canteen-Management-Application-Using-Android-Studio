package com.example.canteen.canteen_management;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class Manager_login extends AppCompatActivity {


    private static final String TAG = "Manager_login";


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    private EditText emailEditText,passwordEditText;
    private Button btnLogin;
    private TextView createAccountTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);




        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.login_btn);
        createAccountTextView = findViewById(R.id.createAccountTextView);
        progressBar = findViewById(R.id.progressBarLogin);


        firebaseAuth = FirebaseAuth.getInstance();




        setupFirebaseAuth();



        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Manager_login.this,"Inside function",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Manager_login.this ,Signup_page.class );
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                Log.d(TAG, "onClick: Button clicked");
                if(email.equals("") || password.equals(""))
                {
                    Toast.makeText(Manager_login.this, "Please enter the text", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    login(email, password);
                }
            }
        });

    }


    private void login(String email,String password){
        Log.d(TAG, "login: Signing in");
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Log.d(TAG,"after getting user");
                            Intent intent = new Intent(Manager_login.this,Manager_Menu.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Manager_login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);

                        }

                        // ...
                    }
                });
    }

    private void setupFirebaseAuth(){
        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    Log.d(TAG, "onAuthStateChanged: Signed in");

                }
                else{
                    Log.d(TAG, "onAuthStateChanged: Signed out");
                }
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        emailEditText.setText("");
        passwordEditText.setText("");
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null)
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

}
