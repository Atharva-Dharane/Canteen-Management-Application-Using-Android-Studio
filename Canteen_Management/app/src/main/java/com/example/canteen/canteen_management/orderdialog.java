package com.example.canteen.canteen_management;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.AtomicFile;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteen.canteen_management.Models.FoodItemsCustomer;
import com.example.canteen.canteen_management.Models.Orders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class orderdialog extends AppCompatActivity {
    private String tablenum;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    CollectionReference mref;
    CollectionReference oref;
    private ArrayList<Integer> foodcount = new ArrayList<>();
    private ArrayList<String> foodnames = new ArrayList<>();
    private ArrayList<String> foodprice = new ArrayList<>();
    String  table;
    private Button backbtn ;
    private Button confirmbtn;
    private EditText tablenumb;
    private TextView displayor;
    private static final String TAG = "orderdialog";
    ArrayList<FoodItemsCustomer> foodItemsCustomers = new ArrayList<>();
    ArrayList<Orders>  orders= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdialog);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        mref = firebaseFirestore.collection("Orders");
        oref = firebaseFirestore.collection("Orderno");

        Intent intent = getIntent();
        foodcount=intent.getIntegerArrayListExtra("Itemcount");
        foodnames=intent.getStringArrayListExtra("Itemname");
        foodprice=intent.getStringArrayListExtra("Itemprice");
        displayor = findViewById(R.id.displayorder);
        calculate();
        backbtn = (Button)findViewById(R.id.orderbackbtn);
        confirmbtn =(Button)findViewById(R.id.orderconfirmbtn);
        tablenumb = (EditText)findViewById(R.id.tableno);
        setupfirebaseauth();
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(orderdialog.this,custumor_menu.class);
                startActivity(i1);
            }
        });

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tablenum = tablenumb.getText().toString();
                if(tablenum.equals(""))
                {
                    Toast.makeText(orderdialog.this, "Please enter the text", Toast.LENGTH_SHORT).show();
                }
                else {
                    senddata();
                }
            }
        });



    }

    private void calculate(){
        String order = "";
        int total= 0;
        for(int i=0;i<foodprice.size();i++)
        {
               order+=foodnames.get(i) + "\t\t\t\t\t\t" + foodprice.get(i) + "\t\t\t\t\t\t" + foodcount.get(i)+ "\n";
        }
        for(int i=0;i<foodprice.size();i++)
        {
            total+=Integer.parseInt(foodprice.get(i))*foodcount.get(i);
        }

        Log.d(TAG, "calculate: "+order);
        order += "\nTotal: " + "\t\t\t\t\t\t" + total;
        displayor.setText(order);
    }

    private void senddata(){
        for(int i=0;i<foodcount.size();i++) {
            int price = Integer.parseInt(foodprice.get(i))*foodcount.get(i);
            Log.d(TAG, "price: "+price);
            Orders orders1 = new Orders(foodnames.get(i),price,foodcount.get(i),tablenum);
            orders.add(orders1);
            Log.d(TAG, "senddata: " + orders);
        }
        firebaseFirestore.collection("Orders");
        for(Orders orders : orders){

            mref.document(orders.getTablenumber()).collection("Orderno").document().set(orders).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "onComplete: Data added");
                }
                else{
                    Log.d(TAG, "onComplete: Failed to add data");
                }
            }
        });
        }
        Intent i2 = new Intent(orderdialog.this, suggestion.class);
        startActivity(i2);

    }

    private void setupfirebaseauth()
    {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
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
