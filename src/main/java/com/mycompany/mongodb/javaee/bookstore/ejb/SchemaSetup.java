/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.ejb;

import com.google.gson.Gson;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.mycompany.mongodb.javaee.bookstore.bean.Book;
import com.mycompany.mongodb.javaee.bookstore.db.BookStoreMongoDBConstants;
import com.mycompany.mongodb.javaee.bookstore.db.MongoDBManager;
import com.mycompany.mongodb.javaee.bookstore.factory.BookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class SchemaSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaSetup.class);

    @Inject
    private MongoDBManager mongoDBManager;

    @PostConstruct
    public void setupData() {
        try {

            DBCollection collection = mongoDBManager.getCollection(BookStoreMongoDBConstants.COLLECTION_NAME);
            collection.drop();
            collection = mongoDBManager.getCollection(BookStoreMongoDBConstants.COLLECTION_NAME);

            Gson gson = new Gson();

            for (Book book : BookFactory.getBooksToAdd()) {
                DBObject object = (DBObject) JSON.parse(gson.toJson(book));
                collection.insert(object);
            }

        } catch (Exception ex) {
            LOGGER.error("{}", ex);
        }
    }

}
