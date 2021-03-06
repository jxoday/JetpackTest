package com.jinxin.jetpacktest.databinding;

/**
 * @author JinXin 2020/9/17
 */
public class Book {

    private String title;

    private String author;
    private String image;

    private int rating;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, String image) {
        this.title = title;
        this.author = author;
        this.image = image;
    }

    public Book(String title, String author, int rating) {
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                '}';
    }
}
