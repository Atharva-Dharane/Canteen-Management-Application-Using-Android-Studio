package com.example.canteen.canteen_management;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.canteen.canteen_management.Models.FoodItems;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Update_menu_page extends AppCompatActivity implements menu_dialogue.OnInputListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private RecyclerView recyclerView;
    private ArrayList<FoodItems> mFoodItems;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;
    private static final String TAG = "Update_menu_page";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu_page);

        mFoodItems = new ArrayList<>();
        recyclerView = findViewById(R.id.list_menu);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

//        collectionReference = firebaseFirestore.collection("Users");
        setupFirebaseAuth();
        ImageView iv = (ImageView) findViewById(R.id.add);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_dialogue menu_dialogue = new menu_dialogue();
                menu_dialogue.show(getFragmentManager(),"menu_dialogue");
            }
        });
    }

    @Override
    public void sendInput(String itemName, String price, boolean newElement, FoodItems foodItems,int index) {
        if(newElement){
            sendData(itemName,price);

        }
        else{
            mFoodItems.get(index).setName(itemName);
            mFoodItems.get(index).setPrice(price);
            sendData(itemName,price);
        }
    }

    private void sendData(String itemName, String price){
        FoodItems item = new FoodItems(itemName,price,true);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseFirestore.getInstance().collection("Menu").document(item.getName()).set(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Update_menu_page.this,"Item added!",Toast.LENGTH_SHORT).show();
                }
                else{

                }
            }
        });
        getData();
    }



    private void getData(){
        Log.d(TAG, "getData: " + mFoodItems.toString());
        Log.d(TAG, "getData: size:" + mFoodItems.size());
        mFoodItems.clear();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseFirestore.getInstance().collection("Menu").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent( QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "onEvent: " + e.getMessage());
                }
                else{
                    List<DocumentSnapshot> querySnapshots = queryDocumentSnapshots.getDocuments();

                    mFoodItems.clear();
                    for(DocumentSnapshot documentSnapshot : querySnapshots){
                        mFoodItems.add(documentSnapshot.toObject(FoodItems.class));
                    }
                    setupRecyclerView();
                }


            }
        });



    }

    private void setupRecyclerView(){
        updatemenu_recycler_adapter mupdatemenu_recycler_adapter = new updatemenu_recycler_adapter(Update_menu_page.this,mFoodItems,FirebaseFirestore.getInstance().collection("Menu"));
        recyclerView.setAdapter(mupdatemenu_recycler_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupFirebaseAuth(){
        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    Log.d(TAG, "onAuthStateChanged: Signed in");
                    getData();
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
        mFoodItems.clear();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        firebaseAuth.removeAuthStateListener(authStateListener);
    }

}

