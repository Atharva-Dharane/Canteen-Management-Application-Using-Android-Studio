package com.example.canteen.canteen_management;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.canteen.canteen_management.Models.FoodItemsCustomer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class custumor_menu extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<Integer> foodcount = new ArrayList<>();
    private ArrayList<String> foodnames = new ArrayList<>();
    private ArrayList<String> foodprice = new ArrayList<>();
    private ArrayList<FoodItemsCustomer> foodItemsCustomers = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private CollectionReference foodItemsCollection;

    private static final String TAG = "custumor_menu";
    Button place_or;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custumor_menu);


        recyclerView = findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();

        foodItemsCollection = FirebaseFirestore.getInstance().collection("Menu");

        place_or = (Button) findViewById(R.id.place_order);

        place_or.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem();
                Intent i1=new Intent(custumor_menu.this,orderdialog.class);
                i1.putIntegerArrayListExtra("Itemcount",foodcount);
                i1.putStringArrayListExtra("Itemname",foodnames);
                i1.putStringArrayListExtra("Itemprice",foodprice);
                startActivity(i1);

            }
        });

        setupFirebaseAuth();
    }

    private void additem()
    {

        for(FoodItemsCustomer foodItemsCustomer : foodItemsCustomers)
        {

            if(foodItemsCustomer.getCount()!=0)
            {
                foodnames.add(foodItemsCustomer.getName());
                foodprice.add(foodItemsCustomer.getPrice());
                foodcount.add(foodItemsCustomer.getCount());
            }
        }
    }

    private void setupFirebaseAuth(){
        
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged: signed in");
                    getData();
                }
                else{
                    Log.d(TAG, "onAuthStateChanged: singed out");
                }
            }
        };
        
    }


    private void getData(){

        foodItemsCollection.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent( QuerySnapshot queryDocumentSnapshots,  FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "onEvent: " + e.getMessage());
                }
                else{
                    foodItemsCustomers.clear();
                    for(DocumentSnapshot doc : queryDocumentSnapshots){
                        if (doc.toObject(FoodItemsCustomer.class).isAvailable()) {
                            foodItemsCustomers.add(doc.toObject(FoodItemsCustomer.class));
                        }
                        }
                    setupRecyclerView();
                }

            }
        });

    }



    private void setupRecyclerView(){
        RecyclerViewCustomerAdapter recyclerViewCustomerAdapter = new RecyclerViewCustomerAdapter(foodItemsCustomers,this,place_or);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewCustomerAdapter);
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
