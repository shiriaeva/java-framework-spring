package com.example.mybookshopapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentsPageController {

    @GetMapping("/documents")
    public String documentsPage(){
        return "documents/index";
    }

    @GetMapping("/documents_info")
    public String infoPage(){
        return "documents/slug";
    }
}
