/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.factory;

import com.mycompany.mongodb.javaee.bookstore.bean.Book;

import java.util.ArrayList;
import java.util.List;

public class BookFactory {

    public static List<Book> getBooksToAdd() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(10, "Novel", "Charles Dickens", "A Tale of Two Cities", 10));
        books.add(new Book(12, "Thriller", "Dan Brown", "The Da Vinci Code", 10));
        books.add(new Book(10, "Motivation", "Napoleon Hill", "Think and Grow Rich", 10));
        books.add(new Book(8, "Fantasy", "J.R.R. Tolkien", "The Hobbit", 10));
        books.add(new Book(8, "Novel", "Antoine de Saint-Exupery", "Le Petit Prince", 10));
        return books;
    }

}
