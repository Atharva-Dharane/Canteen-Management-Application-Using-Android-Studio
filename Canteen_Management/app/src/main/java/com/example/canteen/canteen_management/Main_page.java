package com.example.canteen.canteen_management;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_page extends AppCompatActivity {

    private static final String TAG = "Main_page";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Button customer = (Button)findViewById(R.id.button1);
        Button manager = (Button)findViewById(R.id.button2);

        firebaseAuth = FirebaseAuth.getInstance();

        setupFirebaseAuth();
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        if(user != null){

                            Intent i = new Intent(Main_page.this , Manager_Menu.class);
                            startActivity(i);
                        }else{

                            Intent i = new Intent(Main_page.this , Manager_login.class);
                            startActivity(i);
                        }
                    }



        });

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main_page.this , custumor_menu.class);
                startActivity(i);
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
