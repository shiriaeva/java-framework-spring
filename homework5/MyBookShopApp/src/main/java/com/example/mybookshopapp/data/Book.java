package com.example.mybookshopapp.data;

import com.example.mybookshopapp.data.book.file.FileDownloadEntity;
import com.example.mybookshopapp.data.book.links.Book2AuthorEntity;
import com.example.mybookshopapp.data.book.links.Book2GenreEntity;
import com.example.mybookshopapp.data.book.links.Book2UserEntity;
import com.example.mybookshopapp.data.book.review.BookReviewEntity;
import com.example.mybookshopapp.data.payments.BalanceTransactionEntity;
import com.example.mybookshopapp.data.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pub_date", columnDefinition = "DATE", nullable = false)
    @JsonIgnore
    private Date pubDate;

    @Column(name = "is_bestseller", columnDefinition = "INT", nullable = false)
    private Integer isBestseller;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String slug;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String title;

    @Column(columnDefinition = "VARCHAR(255)")
    private String image;

    @Column(columnDefinition = "TEXT")
    @JsonIgnore
    private String description;

    @ManyToMany
    @JoinTable(
            name = "book2author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnore
    private List<Author> authors;

    @JsonGetter("authors")
    public String getAuthorsName(){
        String name = "";
        int size = authors.size();
        for(Author a: authors){
            if(size > 1){
                name = name + a.getName() + "\n";
            }else if (size == authors.size()) {
                name = name + a.getName();
            }
        }
        return name;
    }

    @Column(name = "price", columnDefinition = "INT", nullable = false)
    @JsonProperty("price")
    private Integer priceOld;

    @Column(name = "discount", columnDefinition = "NUMERIC DEFAULT 0", nullable = false)
    @JsonProperty("discount")
    private double price;

    public Integer discount(){
        return Math.toIntExact(Math.round(price * 100));
    }

    @JsonProperty
    public Integer discountPrice(){
        if(price < 0.1){
            return priceOld;
        }
        return priceOld - Math.toIntExact(Math.round(price * priceOld));
    }

    @JsonProperty
    public Integer rating(){
        return 5;
    }

    @JsonProperty
    public String status(){
        return "KEPT";
    }

    @ManyToMany
    @JoinTable(
            name = "book2genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @JsonIgnore
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "book2user",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "file_download",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> fileDownload;

    @ManyToMany
    @JoinTable(
            name = "balance_transaction",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> balanceTransaction;

    @ManyToMany
    @JoinTable(
            name = "book_review",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<UserEntity> bookReview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Integer isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Integer getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(Integer priceOld) {
        this.priceOld = priceOld;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0.1){
            this.price = 0;
        }else {
            this.price = price;
        }
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<UserEntity> getFileDownload() {
        return fileDownload;
    }

    public void setFileDownload(List<UserEntity> fileDownload) {
        this.fileDownload = fileDownload;
    }

    public List<UserEntity> getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(List<UserEntity> balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }

    public List<UserEntity> getBookReview() {
        return bookReview;
    }

    public void setBookReview(List<UserEntity> bookReview) {
        this.bookReview = bookReview;
    }
}
