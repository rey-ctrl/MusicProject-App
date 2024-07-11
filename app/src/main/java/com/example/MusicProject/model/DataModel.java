package com.example.MusicProject.model;

public class DataModel {

    private String UID; // Add UID field
    private String ArtistTitle;
    private String ArtistCategory;
    private String ArtistImage;

    public DataModel() {
    }

    public DataModel(String UID, String artistTitle, String artistCategory, String artistImage) {
        this.UID = UID;
        this.ArtistTitle = artistTitle;
        this.ArtistCategory = artistCategory;
        this.ArtistImage = artistImage;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getArtistTitle() {
        return ArtistTitle;
    }

    public void setArtistTitle(String artistTitle) { this.ArtistTitle = artistTitle; }

    public String getArtistCategory() {
        return ArtistCategory;
    }

    public void setArtistCategory(String artistCategory) {
        this.ArtistCategory = artistCategory;
    }

    public String getArtistImage() { return ArtistImage; }

    public void setArtistImage(String artistImage) {
        this.ArtistImage = artistImage;
    }
}
