package com.example.MusicProject.model;

public class HomeModel {

    String Name;
    String Image;

    public HomeModel() {


    }

    public HomeModel(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
