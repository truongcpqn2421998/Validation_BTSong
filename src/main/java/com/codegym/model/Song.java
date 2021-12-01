package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Table
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String NameSong;
    private String singer;
    private String category;
    private String listen;

    public Song() {
    }

    public Song(Long id, String nameSong, String singer, String category, String listen) {
        this.id = id;
        NameSong = nameSong;
        this.singer = singer;
        this.category = category;
        this.listen = listen;
    }

    public Song(String nameSong, String singer, String category, String listen) {
        NameSong = nameSong;
        this.singer = singer;
        this.category = category;
        this.listen = listen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSong() {
        return NameSong;
    }

    public void setNameSong(String nameSong) {
        NameSong = nameSong;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }


}
