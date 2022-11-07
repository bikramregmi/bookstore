package com.bikram.bookstore.domain;


import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_name")
    private String bookName;

    @OneToOne
    private BookCategory bookCategory;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "book_price")
    private double bookPrice;

    @Column(name = "purchased by")
    private String purchaseBy;

    public Book(long id, String bookName, BookCategory bookCategory, String bookAuthor, LocalDate publishedDate, double bookPrice, String purchaseBy) {
        this.id = id;
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.bookAuthor = bookAuthor;
        this.publishedDate=publishedDate;
        this.bookPrice = bookPrice;
        this.purchaseBy = purchaseBy;
    }

    public Book() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getPurchaseBy() {
        return purchaseBy;
    }

    public void setPurchaseBy(String purchaseBy) {
        this.purchaseBy = purchaseBy;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookCategory=" + bookCategory +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publishedDate=" + publishedDate +
                ", bookPrice=" + bookPrice +
                ", purchaseBy='" + purchaseBy + '\'' +
                '}';
    }
}
