package com.example.canteen.canteen_management;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.canteen.canteen_management.Models.Review;

import java.util.ArrayList;

public class RecyclerViewAdapterReview extends RecyclerView.Adapter<RecyclerViewAdapterReview.ReviewViewHolder> {

    ArrayList<Review> reviews;
    Context mContext;

    public RecyclerViewAdapterReview(ArrayList<Review> reviews, Context mContext) {
        this.reviews = reviews;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review,parent,false);

        return new ReviewViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.messageTextView.setText(reviews.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        TextView messageTextView;
        RelativeLayout parentLayout;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            messageTextView = itemView.findViewById(R.id.reviewTextView);
        }
    }
}
