package com.dambana.books;

public class ISBNBook {
    public String id;
    public String title;
    public String subTitle;
    public String[] authors;
    public String publisher;
    public String publishedDate;
    public long isbn;

    public ISBNBook(String id, String title, String subTitle, String[] authors, String publisher, String publishedDate, long isbn) {

        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.isbn = isbn;
    }
}
