package com.example.xstrike.facebook_search_by_tag.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xstrike.facebook_search_by_tag.R;
import com.example.xstrike.facebook_search_by_tag.beans.DateOfPlace;

import java.util.List;

public class PreviewRecyclerAdapter extends RecyclerView.Adapter<PreviewRecyclerAdapter.ViewHolder> {
    private List<DateOfPlace> listItems;
    private Context context;

    public PreviewRecyclerAdapter(List<DateOfPlace> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_of_card_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DateOfPlace dateOfPlace = listItems.get(position);

        holder.text_phone.setText(dateOfPlace.getPhone());
        holder.text_rating_count.setText(dateOfPlace.getRating_cont());
        holder.text_name.setText(dateOfPlace.getName());
        holder.text_location.setText(dateOfPlace.getLatitude() + " " + dateOfPlace.getLongitude());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_name, text_location, text_rating_count, text_phone;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            text_location = itemView.findViewById(R.id.text_location);
            text_name = itemView.findViewById(R.id.text_name);
            text_rating_count = itemView.findViewById(R.id.text_rating_count);
            text_phone = itemView.findViewById(R.id.text_phone);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
