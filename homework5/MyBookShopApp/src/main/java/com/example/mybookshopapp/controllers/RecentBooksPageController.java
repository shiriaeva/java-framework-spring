package com.example.mybookshopapp.controllers;

import com.example.mybookshopapp.data.Book;
import com.example.mybookshopapp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class RecentBooksPageController {

    private final BookService bookService;

    @Autowired
    public RecentBooksPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("booksList")
    public List<Book> bookList(){
        return bookService.getBooksData();
    }

    @GetMapping("/books/recent")
    public String recentBookPage(){
        return "books/recent";
    }
}
