package com.example.MusicProject.model;

public class ViewAllModel {

    private String UID;
    String ArtistImage;
    String ArtistTitle;
    String ArtistCategory;

    public ViewAllModel() {
    }

    public ViewAllModel(String UID, String artistImage, String artistTitle, String artistCategory) {
        this.UID = UID;
        ArtistImage = artistImage;
        ArtistTitle = artistTitle;
        ArtistCategory = artistCategory;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getArtistImage() {
        return ArtistImage;
    }

    public void setArtistImage(String artistImage) {
        ArtistImage = artistImage;
    }

    public String getArtistTitle() {
        return ArtistTitle;
    }

    public void setArtistTitle(String artistTitle) {
        ArtistTitle = artistTitle;
    }

    public String getArtistCategory() {
        return ArtistCategory;
    }

    public void setArtistCategory(String artistCategory) {
        ArtistCategory = artistCategory;
    }
}
