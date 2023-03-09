package com.example.canteen.canteen_management;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.canteen.canteen_management.Models.FoodItemsCustomer;

import java.util.ArrayList;

public class RecyclerViewCustomerAdapter extends RecyclerView.Adapter<RecyclerViewCustomerAdapter.ViewHolder>{

    ArrayList<FoodItemsCustomer> foodItems;
    Context mContext;
    private static final String TAG = "RecyclerViewCustomerAda";
    Button bt;
    public RecyclerViewCustomerAdapter(ArrayList<FoodItemsCustomer> foodItems, Context mContext,Button btn) {
        this.foodItems = foodItems;
        this.mContext = mContext;
        this.bt = btn;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_food,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.foodItemCount.setText(Integer.toString(foodItems.get(position).getCount()));
        holder.foodItemName.setText(foodItems.get(position).getName());
        holder.foodItemPrice.setText(foodItems.get(position).getPrice());

        holder.btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = foodItems.get(position).getCount();
                Log.d(TAG, "onClick: cliced");
                count++;
                foodItems.get(position).setCount(count);
                bt.setVisibility(View.VISIBLE);
                notifyDataSetChanged();
            }
        });

        holder.btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(foodItems.get(position).getCount() > 0) {
                    int count = foodItems.get(position).getCount();
                    count--;
                    foodItems.get(position).setCount(count);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        TextView foodItemName,foodItemPrice,foodItemCount;
        Button btnIncrement,btnDecrement;


        public ViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            foodItemName = itemView.findViewById(R.id.foodItemName);
            foodItemPrice = itemView.findViewById(R.id.foodItemPrice);
            foodItemCount = itemView.findViewById(R.id.foodItemCount);
            btnIncrement = itemView.findViewById(R.id.btnIncrementCount);
            btnDecrement = itemView.findViewById(R.id.btnDecrementCount);

        }
    }
}
