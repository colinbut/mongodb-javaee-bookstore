/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import javax.inject.Inject;

public class MongoDBManager {

    private static MongoDBManager mongoDBManager = new MongoDBManager();

    @Inject
    private MongoClient mongoClient;

    public static MongoDBManager getMongoDBManager() {
        return mongoDBManager;
    }

    private DB getDB() {
        return mongoClient.getDB("bookstore");
    }

    public DBCollection getCollection(String collectionName) {
        DB db = getDB();
        return db.getCollection(collectionName);
    }
}
