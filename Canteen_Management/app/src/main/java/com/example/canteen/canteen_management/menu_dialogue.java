package com.example.canteen.canteen_management;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canteen.canteen_management.Models.FoodItems;
import com.example.canteen.canteen_management.R;

public class menu_dialogue extends DialogFragment {
    TextView okTextView,cancelTextView;
    EditText nameEditText,priceEditText;
    FoodItems foodItems;
    Boolean editing;
    private static final String TAG = "menu_dialogue";
    public interface OnInputListener{
        void sendInput(String itemName,String price,boolean newElement,FoodItems foodItems,int index);
    }
    public OnInputListener mOnInputListener;
    int index;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.menu_dialogue,container,false);
        editing = false;
        okTextView = v.findViewById(R.id.ok);
        cancelTextView = v.findViewById(R.id.cancel);

        nameEditText = v.findViewById(R.id.item_name);
        priceEditText = v.findViewById(R.id.price);
        try {
            if (getArguments().getSerializable("foodItem") != null) {
                editing = true;
                foodItems = (FoodItems) getArguments().getSerializable("foodItem");

                nameEditText.setText(foodItems.getName());
                priceEditText.setText(foodItems.getPrice());
                index = getArguments().getInt("foodItemPosition");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = nameEditText.getText().toString();
                String itemPrice = priceEditText.getText().toString();

                if(itemName.equals("") || itemPrice.equals(""))
                {
                    Toast.makeText(getActivity(), "Please enter the text", Toast.LENGTH_SHORT).show();
                }
                if (editing){
                    foodItems.setName(itemName);
                    foodItems.setPrice(itemPrice);

                    mOnInputListener.sendInput(itemName,itemPrice,false,foodItems,index);

                }
                else{
                    mOnInputListener.sendInput(itemName,itemPrice,true,null,0);
                }

                getDialog().dismiss();
            }
        });

        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mOnInputListener = (OnInputListener) getActivity();
        }
        catch (ClassCastException e){
            Log.d(TAG, "onAttach: " + e.getMessage());
        }
    }
}
