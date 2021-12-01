package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

public class SongForm implements Validator {
    private Long id;
    private String NameSong;
    private String singer;
    private String category;
    private MultipartFile listen;

    public SongForm() {
    }

    public SongForm(Long id, String nameSong, String singer, String category, MultipartFile listen) {
        this.id = id;
        NameSong = nameSong;
        this.singer = singer;
        this.category = category;
        this.listen = listen;
    }

    public SongForm(String nameSong, String singer, String category, MultipartFile listen) {
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

    public MultipartFile getListen() {
        return listen;
    }

    public void setListen(MultipartFile listen) {
        this.listen = listen;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SongForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SongForm song=(SongForm) target;
        String nameSong=song.getNameSong();
        String singer=song.getSinger();
        String category=song.getCategory();
        ValidationUtils.rejectIfEmpty(errors,"nameSong","nameSong.empty");
        ValidationUtils.rejectIfEmpty(errors,"singer","singer.empty");
        ValidationUtils.rejectIfEmpty(errors,"category","category.empty");

        if(nameSong.length()>800){
            errors.rejectValue("nameSong","nameSong.length");
        }
        if(singer.length()>800){
            errors.rejectValue("singer","singer.length");
        }
        if(category.length()>800){
            errors.rejectValue("category","category.length");
        }

        if(!nameSong.matches("(^[a-zA-Z0-9]*$)")){
            errors.rejectValue("nameSong","nameSong.matches");
        }

        if(!singer.matches("(^[a-zA-Z0-9]*$)")){
            errors.rejectValue("singer","singer.matches");
        }
        if(!category.matches("(^[a-zA-Z0-9]*[^,]$)")){
            errors.rejectValue("category","category.matches");
        }

    }
}
