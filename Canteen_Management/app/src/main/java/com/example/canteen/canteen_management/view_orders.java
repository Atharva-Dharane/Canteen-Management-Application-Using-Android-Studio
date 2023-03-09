package com.example.canteen.canteen_management;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteen.canteen_management.Models.Orders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class view_orders extends AppCompatActivity {
   int it=1;
    public int i;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "view_orders";
    CollectionReference mref;
    TextView tables1;
    TextView tables2;
    TextView tables3;
    TextView tables4;
    TextView tables5;
    TextView tables6;
    TextView tables7;
    TextView tables8;
    TextView tables9;
    TextView tables10;

    CardView cv1;
    CardView cv2;
    CardView cv3;
    CardView cv4;
    CardView cv5;
    CardView cv6;
    CardView cv7;
    CardView cv8;
    CardView cv9;
    CardView cv10;


    ArrayList<Orders> orders10 = new ArrayList<>();
    ArrayList<Orders> orders1 = new ArrayList<>();
    ArrayList<Orders> orders2 = new ArrayList<>();
    ArrayList<Orders> orders3 = new ArrayList<>();
    ArrayList<Orders> orders4 = new ArrayList<>();
    ArrayList<Orders> orders5 = new ArrayList<>();
    ArrayList<Orders> orders6= new ArrayList<>();
    ArrayList<Orders> orders7 = new ArrayList<>();
    ArrayList<Orders> orders8 = new ArrayList<>();
    ArrayList<Orders> orders9 = new ArrayList<>();



    private String[] id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mref = firebaseFirestore.collection("Orders");
        setupfirebaseauth();
        tables1 = findViewById(R.id.tb1);
        tables2 = findViewById(R.id.tb2);
        tables3 = findViewById(R.id.tb3);
        tables4 = findViewById(R.id.tb4);
        tables5 = findViewById(R.id.tb5);
        tables6 = findViewById(R.id.tb6);
        tables7 = findViewById(R.id.tb7);
        tables8 = findViewById(R.id.tb8);
        tables9 = findViewById(R.id.tb9);
        tables10 = findViewById(R.id.tb10);


        cv1 = findViewById(R.id.table1);
        cv2 = findViewById(R.id.table2);
        cv3 = findViewById(R.id.table3);
        cv4 = findViewById(R.id.table4);
        cv5 = findViewById(R.id.table5);
        cv6 = findViewById(R.id.table6);
        cv7 = findViewById(R.id.table7);
        cv8 = findViewById(R.id.table8);
        cv9= findViewById(R.id.table9);
        cv10 = findViewById(R.id.table10);
        getdata();

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("1").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                tables1.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("1").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables1.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("2").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables2.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("3").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables3.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("4").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables4.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("5").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables5.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("6").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables6.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("7").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables7.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("8").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables8.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("9").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables9.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });

        cv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Orders").document("10").delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(view_orders.this, "Order Successfully Delivered !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                tables10.setText(" ");
                Log.d(TAG, "onClick: Inside click");

            }
        });


    }

    private void getdata() {
        for (int i = 1; i < 11; i++) {
            switch (i) {
                case 1:
                    mref.document("1").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables1.setText(ord);

                                }
                            }
                            orders1.clear();


                        }

                    });
                    it += 1;
                    break;

                case 2:
                    Log.d(TAG, "getdata: Table 2");
                    mref.document("2").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables2.setText(ord);

                                }
                                Log.d(TAG, "onComplete: " + it + ord);
                                Log.d(TAG, "getdata: retrieved data for table " + it);

                            } else {
                                Log.d(TAG, "onComplete: Failed to retrieve data : " + it);

                            }
                            orders1.clear();

                        }

                    });
                    it += 1;
                    break;


                case 3:
                    mref.document("3").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables3.setText(ord);
                                }

                            }
                            orders1.clear();

                        }

                    });
                    it += 1;
                    break;


                case 4:
                    Log.d(TAG, "getdata: Table 4");
                    mref.document("4").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables4.setText(ord);
                                }
                                Log.d(TAG, "onComplete: " + it + ord);
                                Log.d(TAG, "getdata: retrieved data for table " + it);

                            } else {
                                Log.d(TAG, "onComplete: Failed to retrieve data : " + it);

                            }
                            orders1.clear();

                        }

                    });
                    it += 1;
                    break;

                    case 5:
                    mref.document("5").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables5.setText(ord);
                                }

                            }
                            orders1.clear();

                        }

                    });
                    it +=1;
                    break;
                case 6:
                    mref.document("6").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables6.setText(ord);
                                }

                            }
                            orders1.clear();

                        }

                    });
                    it +=1;
                    break;
                case 7:
                    mref.document("7").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables7.setText(ord);
                                }
                                orders1.clear();

                            }

                        }

                    });
                    it +=1;
                    break;
                case 8:
                    mref.document("8").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables8.setText(ord);
                                }

                            }
                            orders1.clear();

                        }

                    });
                    it +=1;
                    break;
                case 9:
                    mref.document("9").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables9.setText(ord);
                                }

                            }
                            orders1.clear();

                        }

                    });
                    it +=1;
                    break;
                case 10:
                    mref.document("10").collection("Orderno").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "onComplete: i before arraylist" + it);
                                List<DocumentSnapshot> docs = task.getResult().getDocuments();
                                for (DocumentSnapshot doc : docs) {
                                    orders1.add(doc.toObject(Orders.class));
                                }
                                String ord = "";
                                for (Orders orders : orders1) {
                                    ord += orders.getFoodname() + "\t" + orders.getFoodcount() + "\n";
                                    tables10.setText(ord);
                                }

                            }
                            orders1.clear();

                        }

                    });
                    it =0;
                    break;

            }
        }
    }

    private void setupfirebaseauth()
    {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

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
