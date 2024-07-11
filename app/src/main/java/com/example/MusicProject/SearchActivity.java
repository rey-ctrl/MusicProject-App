package com.example.MusicProject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MusicProject.adapter.SearchAdapter;
import com.example.MusicProject.model.DataModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView searchRecyclerView;
    private SearchAdapter searchAdapter;
    private List<DataModel> searchResults;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        searchRecyclerView = findViewById(R.id.searchRecyclerView);

        searchResults = new ArrayList<>();
        searchAdapter = new SearchAdapter(this, searchResults);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setAdapter(searchAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Artists");

        loadAllArtists();

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.onActionViewExpanded();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchArtists(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    loadAllArtists();
                } else {
                    searchArtists(newText);
                }
                return false;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), DashboardUserActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_search) {
                return true;
            } else if (itemId == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else {
                return false;
            }
        });
    }

    private void loadAllArtists() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                searchResults.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String title = snapshot.child("ArtistTitle").getValue(String.class);
                    String category = snapshot.child("ArtistCategory").getValue(String.class);
                    String image = snapshot.child("ArtistImage").getValue(String.class);

                    DataModel artist = new DataModel(uid, title, category, image);
                    searchResults.add(artist);
                }
                searchAdapter.updateList(searchResults);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Failed to read data", databaseError.toException());
            }
        });
    }

    private void searchArtists(String query) {
        String lowerCaseQuery = query.toLowerCase();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                searchResults.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String title = snapshot.child("ArtistTitle").getValue(String.class);
                    String category = snapshot.child("ArtistCategory").getValue(String.class);
                    String image = snapshot.child("ArtistImage").getValue(String.class);

                    if (title != null && title.toLowerCase().contains(lowerCaseQuery)) {
                        DataModel artist = new DataModel(uid, title, category, image);
                        searchResults.add(artist);
                    }
                }
                searchAdapter.updateList(searchResults);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Failed to read data", databaseError.toException());
            }
        });
    }

    public void onBackButtonClick(View view) {
        onBackPressed();
    }
}