package com.example.MusicProject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MusicProject.ArtistDetailActivity;
import com.example.MusicProject.R;
import com.example.MusicProject.model.DataModel;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<DataModel> searchResults;
    private Context context;

    public SearchAdapter(Context context, List<DataModel> searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel artist = searchResults.get(position);
        holder.title.setText(artist.getArtistTitle());
        holder.category.setText(artist.getArtistCategory());
        // Load image into ImageView using Glide
        Glide.with(context).load(artist.getArtistImage()).into(holder.image);

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            String uid = artist.getUID();
            if (uid != null) {
                Intent intent = new Intent(context, ArtistDetailActivity.class);
                intent.putExtra("artistUid", uid);
                context.startActivity(intent);
            } else {
                // Handle null UID scenario
                // Optionally show a Toast or handle the case gracefully
                Toast.makeText(context, " UID is missing", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public void updateList(List<DataModel> newList) {
        searchResults = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, category;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.view_all_name);
            category = itemView.findViewById(R.id.view_all_category);
            image = itemView.findViewById(R.id.view_all_img);
        }
    }
}
