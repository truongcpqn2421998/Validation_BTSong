package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@PropertySource("classpath:upload_file.properties")
public class SongController {

    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private ISongService songService;

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView=new ModelAndView("/home");
        Iterable<Song> songs=songService.findAll();
        modelAndView.addObject("songList",songs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate(){
        ModelAndView modelAndView=new ModelAndView("create");
        modelAndView.addObject("songForm",new SongForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute SongForm songForm, BindingResult bindingResult){
        new SongForm().validate(songForm,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView("create");
        }
        MultipartFile multipartFile=songForm.getListen();
        String fileName=multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(songForm.getListen().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Song song=new Song(songForm.getId(),songForm.getNameSong(),songForm.getSinger(),songForm.getCategory(),fileName);
        songService.save(song);
        ModelAndView modelAndView=new ModelAndView("redirect:/home");
        modelAndView.addObject("songForm",songForm);
        modelAndView.addObject("message","create complete");
        return modelAndView;
    }
}
