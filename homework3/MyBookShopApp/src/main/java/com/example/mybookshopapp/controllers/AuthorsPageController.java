package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.AuthorService;
import com.example.mybookshopapp.data.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop")
public class AuthorsPageController {

    private final AuthorService authorService;

    @Autowired
    public AuthorsPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String authorsPage(Model model){
        model.addAttribute("authorData", authorService.getAuthorData());
        return "authors/index";
    }
}
