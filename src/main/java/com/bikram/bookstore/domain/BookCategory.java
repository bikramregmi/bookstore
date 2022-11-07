package com.bikram.bookstore.domain;


import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name="book_category")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="book_branch")
    private String bookBranch;

    @Column(name="book_sub_branch")
    private String bookSubBranch;

    public BookCategory() {

    }

    public BookCategory(long id, String bookBranch, String bookSubBranch) {
        this.id=id;
        this.bookBranch=bookBranch;
        this.bookSubBranch=bookSubBranch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "BookCategory{" +
                "id=" + id +
                ", bookBranch='" + bookBranch + '\'' +
                ", bookSubBranch='" + bookSubBranch + '\'' +
                '}';
    }
}
