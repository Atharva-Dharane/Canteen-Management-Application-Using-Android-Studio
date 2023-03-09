package com.example.canteen.canteen_management;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.canteen.canteen_management.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signup_page extends AppCompatActivity {

    private EditText emailEditText,passwordEditText,nameEditText;
    private static final String TAG = "Signup_page";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressBar progressBarSignup;

    private FirebaseFirestore mRef;
    private CollectionReference userCollection;

    private User user_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        final Button signup = (Button)findViewById(R.id.signbutton);

        firebaseAuth = FirebaseAuth.getInstance();

        mRef = FirebaseFirestore.getInstance();
        userCollection = mRef.collection("Users");

        progressBarSignup = findViewById(R.id.progressBarSignup);
        progressBarSignup.setVisibility(View.GONE);
        emailEditText = findViewById(R.id.emailid);
        passwordEditText = findViewById(R.id.passw);
        nameEditText = findViewById(R.id.name);

        setupFirebaseAuth();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                if(email.equals("") || password.equals("") || name.equals(""))
                {
                    Toast.makeText(Signup_page.this, "Please enter the text", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBarSignup.setVisibility(View.VISIBLE);
                    signup(email, password, name);
                }
                }
        });
    }

    private void signup(final String email, String password, final String name){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            User user1 = new User(name,email,user.getUid());
                            Log.d(TAG, "Goingin " + user1);

                          mRef.collection("Users").document(user1.getUserID()).set(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Log.d(TAG, "onComplete: Data added successfully");
                                    }
                                    else {
                                        Log.d(TAG, "onComplete: failed to add data");
                                    }
                                }
                            });
                            firebaseAuth.signOut();
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup_page.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

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

        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
