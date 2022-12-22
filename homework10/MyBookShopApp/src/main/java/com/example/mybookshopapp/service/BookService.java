package com.example.mybookshopapp.service;

import com.example.mybookshopapp.annotations.MethodExecutionTimeLoggable;
import com.example.mybookshopapp.model.author.Author;
import com.example.mybookshopapp.model.book.Book;
import com.example.mybookshopapp.model.genre.Genre;
import com.example.mybookshopapp.model.tag.TagBook;
import com.example.mybookshopapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @MethodExecutionTimeLoggable
    public Page<Book> getPageBooks(Integer offset, Integer limit, String properties) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.Direction.DESC, properties);
        return bookRepository.findAll(nextPage);
    }

    @MethodExecutionTimeLoggable
    public Page<Book> getPageOfPubDateBetweenBooks(String from, String to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "pubDate"));
        try {
            Date dateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(from);
            Date dateTo = new SimpleDateFormat("dd.MM.yyyy").parse(to);
            return bookRepository.findBookEntityByPubDateBetween(dateFrom, dateTo, nextPage);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @MethodExecutionTimeLoggable
    public Page<Book> getPageOfSearchResultBooks(String wordSearch, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookEntityByTitleContainingAllIgnoreCase(wordSearch, nextPage);
    }

    @MethodExecutionTimeLoggable
    public Page<Book> getBooksForPageTage(TagBook tag, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findByTagList_Slug(tag.getSlug(), nextPage);
    }

    @MethodExecutionTimeLoggable
    public Page<Book> getBooksForPageGenre(Genre genre, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getByGenreList_Slug(genre.getSlug(), nextPage);
    }

    @MethodExecutionTimeLoggable
    public Page<Book> getBooksForPageAuthor(Author author, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.getByAuthorList_Id(author.getId(), nextPage);
    }

    @MethodExecutionTimeLoggable
    public Book getBookBySlug(String slug) {
        return bookRepository.findBookEntityBySlug(slug);
    }

    @MethodExecutionTimeLoggable
    public void save(Book bookToUpdate) {
        bookRepository.save(bookToUpdate);
    }
}