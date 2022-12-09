package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenresPageController {

    private final GenreService genreService;

    @Autowired
    public GenresPageController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String genresPage(Model model){
        model.addAttribute("genreData", genreService.getGenresData());
        return "genres/index";
    }

    @GetMapping("/genres_info")
    public String infoPage(){
        return "genres/slug";
    }
}
