/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mycompany.mongodb.javaee.bookstore.bean.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Model
public class BookStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookStore.class);

    @Inject
    MongoClient mongoClient;

    List<Book> bookList;

    String filter;

    @PostConstruct
    private void init() {
        doQuery();
    }

    public void doQuery() {
        bookList = query();
    }

    public List<Book> query() {
        Gson gson = new Gson();

        DB db = mongoClient.getDB("bookstore-db");
        DBCollection collection = db.getCollection("books");
        DBCursor cursor = null;

        if (filter == null || filter.trim().length() == 0) {
            cursor = collection.find();
        } else {
            DBObject query = new BasicDBObject();
            query.put("title", Pattern.compile(filter));
            cursor = collection.find(query);
        }

        List<Book> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                list.add(gson.fromJson(object.toString(), Book.class));
            }
        } finally {
            cursor.close();
        }

        return list;

    }

    public void buy(Book book) {
        LOGGER.info("Buying book: {}", book);
        Gson gson = new Gson();

        int copiesLeft = book.getCopies() - 1;
        DB db = mongoClient.getDB("bookstore-db");

        DBCollection collection = db.getCollection("books");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("copies", copiesLeft));

        DBObject searchQuery = (DBObject) JSON.parse(gson.toJson(book));
        collection.update(searchQuery, newDocument);

        bookList = query();
    }

    public Book checkAvailability(Book book) {
        Gson gson = new Gson();
        DB db = mongoClient.getDB("bookstore-db");
        DBCollection collection = db.getCollection("books");

        BasicDBObject query = new BasicDBObject("title", book.getTitle());
        query.append("author", book.getAuthor());

        DBObject object = collection.findOne(query);

        if (object == null) {
            return null;
        }

        return gson.fromJson(object.toString(), Book.class);

    }

}
