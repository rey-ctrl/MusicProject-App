package com.example.MusicProject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.MusicProject.databinding.ActivityArtistDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ArtistDetailActivity extends AppCompatActivity {

    private ActivityArtistDetailBinding binding;
    private TextView artistTitleTextView, artistCategoryTextView, artistBiography;
    private String artistUid;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtistDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Init progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        // Get the artist UID from the intent
        artistUid = getIntent().getStringExtra("artistUid");

        // Initialize views
        artistTitleTextView = binding.artistTitle;
        artistCategoryTextView = binding.artistCategory;
        artistBiography = binding.artistBiography;

        if (artistUid == null) {
            Toast.makeText(this, "Artist UID is missing", Toast.LENGTH_SHORT).show();
            finish(); // Tutup aktivitas
            return;
        }

        // Retrieve artist details from Firebase using UID
        DatabaseReference artistReference = FirebaseDatabase.getInstance().getReference("Artists").child(artistUid);
        artistReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String title = dataSnapshot.child("ArtistTitle").getValue(String.class);
                    String category = dataSnapshot.child("ArtistCategory").getValue(String.class);
                    String image = dataSnapshot.child("ArtistImage").getValue(String.class);
                    String biography = dataSnapshot.child("ArtistBiography").getValue(String.class);

                    // Set retrieved data to views
                    Glide.with(ArtistDetailActivity.this).load(image).into(binding.artistImage);
                    artistTitleTextView.setText(title);
                    artistCategoryTextView.setText(category);
                    artistBiography.setText(biography);
                } else {
                    Toast.makeText(ArtistDetailActivity.this, "Artist not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ArtistDetailActivity.this, "Failed to read artist details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to handle back button click
    public void onBackButtonClick(View view) {
        onBackPressed(); // Navigate back to previous activity
    }
}
