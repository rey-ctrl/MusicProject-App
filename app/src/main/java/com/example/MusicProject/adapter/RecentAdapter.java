package com.example.MusicProject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MusicProject.ArtistDetailActivity;
import com.example.MusicProject.R;
import com.example.MusicProject.model.DataModel;

import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {

    private Context context;
    private List<DataModel> dataModelList;

    public RecentAdapter(Context context, List<DataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public RecentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAdapter.ViewHolder holder, int position) {
        DataModel dataModel = dataModelList.get(position);

        // Bind data to views
        Glide.with(context).load(dataModel.getArtistImage()).into(holder.ArtistImage);
        holder.ArtistTitle.setText(dataModel.getArtistTitle());
        holder.ArtistCategory.setText(dataModel.getArtistCategory());
//      holder.UID.setText(recentModel.getUID());

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            // Navigate to DetailActivity and pass UID
            Intent intent = new Intent(context, ArtistDetailActivity.class);
            intent.putExtra("artistUid", dataModel.getUID());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ArtistImage;
        TextView ArtistTitle, ArtistCategory, UID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ArtistImage = itemView.findViewById(R.id.recent_img);
            ArtistTitle = itemView.findViewById(R.id.recent_title);
            ArtistCategory = itemView.findViewById(R.id.recent_category);
            UID = itemView.findViewById(R.id.UID); // Initialize UID TextView
        }
    }
}
