package com.example.canteen.canteen_management;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.canteen.canteen_management.Models.FoodItems;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;

import java.util.ArrayList;

public class updatemenu_recycler_adapter extends RecyclerView.Adapter<updatemenu_recycler_adapter.ViewHolder> {

    Context context;
    ArrayList<FoodItems> foodItems;
    private static final String TAG = "updatemenu_recycler_ada";
    CollectionReference collectionReference;
    public updatemenu_recycler_adapter(Context context, ArrayList<FoodItems> foodItems, CollectionReference collectionReference) {
        Log.d(TAG, "updatemenu_recycler_adapter: " + foodItems.toString());
    this.context = context;
    this.foodItems = foodItems;
    this.collectionReference = collectionReference;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.nameTextView.setText(foodItems.get(position).getName());
        holder.priceTextView.setText(foodItems.get(position).getPrice());

        if(foodItems.get(position).isAvailable()){
            holder.aSwitch.setChecked(true);
        }
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(foodItems.get(position).isAvailable()) {
                    foodItems.get(position).setAvailable(false);
                }
                else{
                    foodItems.get(position).setAvailable(true);
                }
//                notifyDataSetChanged();
                sendData();
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("foodItem", foodItems.get(position));
                bundle.putInt("foodItemPosition", position);

                menu_dialogue fragobj = new menu_dialogue();
                fragobj.setArguments(bundle);

                fragobj.show(((Activity) context).getFragmentManager(),"menu_dialogue");
            }
        });

    }

    private void sendData(){
        for(int i = 0; i < foodItems.size(); i++){
            Log.d(TAG, "sendData: " + foodItems.get(i).getName());
            collectionReference.document(foodItems.get(i).getName()).set(foodItems.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "onSuccess: Data added succesfully");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView nameTextView,priceTextView;
        Switch aSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.parent_layout);
            nameTextView = itemView.findViewById(R.id.food_item_name);
            priceTextView = itemView.findViewById(R.id.food_item_price);
            aSwitch = itemView.findViewById(R.id.switch1);

        }
    }
}
