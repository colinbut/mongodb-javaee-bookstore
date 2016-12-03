/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.ejb;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mycompany.mongodb.javaee.bookstore.bean.Book;
import com.mycompany.mongodb.javaee.bookstore.db.MongoDBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
public class SchemaSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaSetup.class);

    @Inject
    private MongoDBManager mongoDBManager;

    @PostConstruct
    public void setupData() {
        try {

            DBCollection collection = mongoDBManager.getCollection("books");
            collection.drop();
            collection = mongoDBManager.getCollection("books");

            Gson gson = new Gson();

            for (Book book : getBooksToAdd()) {
                DBObject object = (DBObject) JSON.parse(gson.toJson(book));
                collection.insert(object);
            }

        } catch (Exception ex) {
            LOGGER.error("{}", ex);
        }
    }

    private static List<Book> getBooksToAdd() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(10, "Novel", "Charles Dickens", "A Tale of Two Cities"));
        books.add(new Book(12, "Thriller", "Dan Brown", "The Da Vinci Code"));
        books.add(new Book(10, "Motivation", "Napoleon Hill", "Think and Grow Rich"));
        books.add(new Book(8, "Fantasy", "J.R.R. Tolkien", "The Hobbit"));
        books.add(new Book(8, "Novel", "Antoine de Saint-Exupery", "Le Petit Prince"));
        return books;
    }

}
