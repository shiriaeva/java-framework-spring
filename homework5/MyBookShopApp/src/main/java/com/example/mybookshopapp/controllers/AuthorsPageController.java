package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Author;
import com.example.mybookshopapp.data.AuthorService;
import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class AuthorsPageController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorsPageController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, Set<Author>> authorsMap(){
        return authorService.getAuthorData();
    }

    @ModelAttribute("authorBooks")
    public List<Book> bookList(){
        return null;
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "authors/index";
    }

    @GetMapping("/authors_info")
    public String infoPage(){
        return "authors/slug";
    }

    @GetMapping("/author_books")
    public String booksPage(){
        return "books/author";
    }
}
