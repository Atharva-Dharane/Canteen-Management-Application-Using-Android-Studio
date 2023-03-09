package com.example.canteen.canteen_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Manager_Menu extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseauthstatelistner;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(firebaseauthstatelistner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseauthstatelistner!=null){
            firebaseAuth.removeAuthStateListener(firebaseauthstatelistner);
        }
    }

    private void setupfirebaseauth(){
        firebaseauthstatelistner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user=firebaseAuth.getCurrentUser();

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__menu);
        CardView cv = (CardView)findViewById(R.id.upmenu) ;
        CardView order=(CardView)findViewById(R.id.view_order);
        CardView review = findViewById(R.id.reviews);
        firebaseAuth = FirebaseAuth.getInstance();
        setupfirebaseauth();
       cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Manager_Menu.this , Update_menu_page.class);
                startActivity(i);
            }
        });
       order.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(Manager_Menu.this,view_orders.class);
               startActivity(i);
           }
       });

       review.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Manager_Menu.this,Manager_Suggesion.class);
               startActivity(intent);
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_page,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.Logout)
        {
            firebaseAuth.signOut();
            finish();
            Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
