package com.bikram.bookstore.dto;

import com.bikram.bookstore.domain.BookCategory;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class BookDTO {

    private long id;

    private String bookName;

    private long bookCategoryId;

    private String bookBranch;

    private String bookSubBranch;

    private String bookAuthor;

    private LocalDate publishedDate;

    private double bookPrice;

    private String purchaseBy;

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

    public long getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(long bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
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

    public String getBookBranch() {
        return bookBranch;
    }

    public void setBookBranch(String bookBranch) {
        this.bookBranch = bookBranch;
    }

    public String getBookSubBranch() {
        return bookSubBranch;
    }

    public void setBookSubBranch(String bookSubBranch) {
        this.bookSubBranch = bookSubBranch;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookCategoryId=" + bookCategoryId +
                ", bookBranch='" + bookBranch + '\'' +
                ", bookSubBranch='" + bookSubBranch + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", publishedDate=" + publishedDate +
                ", bookPrice=" + bookPrice +
                ", purchaseBy='" + purchaseBy + '\'' +
                '}';
    }
}
