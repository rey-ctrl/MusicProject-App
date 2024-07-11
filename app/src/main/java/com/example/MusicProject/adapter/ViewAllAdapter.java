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
import com.example.MusicProject.R;
import com.example.MusicProject.ArtistDetailActivity;
import com.example.MusicProject.model.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    private Context context;
    private List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewAllModel viewAllModel = viewAllModelList.get(position);

        // Bind data to views
        // Load gambar menggunakan Glide
        String imageUrl = viewAllModel.getArtistImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher) // Placeholder jika gambar tidak tersedia
                    .error(R.mipmap.ic_launcher) // Gambar error jika URL tidak valid
                    .into(holder.ArtistImage);
        } else {
            // Jika URL gambar null atau kosong, tampilkan placeholder
            holder.ArtistImage.setImageResource(R.mipmap.ic_launcher);
        }

        holder.ArtistTitle.setText(viewAllModel.getArtistTitle());
        holder.ArtistCategory.setText(viewAllModel.getArtistCategory());

        holder.itemView.setOnClickListener(v -> {
            String uid = viewAllModel.getUID();
            if (uid != null) {
                Intent intent = new Intent(context, ArtistDetailActivity.class);
                intent.putExtra("artistUid", uid);
                context.startActivity(intent);
            } else {
                // Tangani kasus UID null
                Toast.makeText(context, " UID is missing", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ArtistImage;
        TextView ArtistTitle, ArtistCategory, UID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ArtistImage = itemView.findViewById(R.id.view_all_img);
            ArtistTitle = itemView.findViewById(R.id.view_all_name);
            ArtistCategory = itemView.findViewById(R.id.view_all_category);
            UID = itemView.findViewById(R.id.UID); // Initialize UID TextView
        }
    }
}
