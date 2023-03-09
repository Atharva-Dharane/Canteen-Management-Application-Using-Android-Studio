package com.example.canteen.canteen_management;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.canteen.canteen_management.Models.Review;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Manager_Suggesion extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore ;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    CollectionReference mref;

    private static final String TAG = "Manager_Suggesion";
    ArrayList<Review> reviews = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__suggesion);
        recyclerView = findViewById(R.id.recyclerView);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mref = firebaseFirestore.collection("Reviews");
        setupfirebaseauth();
    }

    private void setupfirebaseauth()
    {
                authStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        getData();
                    }
                };
    }

    private void getData(){
        mref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    List<DocumentSnapshot> docs = task.getResult().getDocuments();

                    for(DocumentSnapshot documentSnapshot : docs){
                        reviews.add(documentSnapshot.toObject(Review.class));

                    }

                    setupRecyclerView();
                }
                else{

                }
            }
        });

    }
    private void setupRecyclerView(){
        RecyclerViewAdapterReview recyclerViewAdapterReview = new RecyclerViewAdapterReview(reviews,Manager_Suggesion.this);
        Log.d(TAG, "setupRecyclerView: " + recyclerViewAdapterReview.getItemCount());
        recyclerView.setAdapter(recyclerViewAdapterReview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
