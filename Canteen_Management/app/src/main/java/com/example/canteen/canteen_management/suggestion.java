package com.example.canteen.canteen_management;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.canteen.canteen_management.Models.Review;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class suggestion extends AppCompatActivity {


    EditText messageEditText;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    CollectionReference reviews;
    Button sendBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        messageEditText = findViewById(R.id.suggestion);
        mAuth = FirebaseAuth.getInstance();
        sendBtn = findViewById(R.id.button);
        reviews = FirebaseFirestore.getInstance().collection("Reviews");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEditText.getText().toString();
                if(message.equals(""))
                {
                    Toast.makeText(suggestion.this, "Please enter Data", Toast.LENGTH_SHORT).show();
                }
                else {

                    sendReview(message);
                }
                }
        });
    }


    private void sendReview(String message){
        Review review = new Review(message);
        reviews.document().set(review).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(suggestion.this,"Review Uploaded",Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(suggestion.this,custumor_menu.class);
                    i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i1);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
}
