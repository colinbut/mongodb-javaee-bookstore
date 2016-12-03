/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.producer;

import com.mongodb.MongoClient;
import com.mycompany.mongodb.javaee.bookstore.MongoDBConnectionProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.UnknownHostException;

@ApplicationScoped
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Produces
    public MongoClient mongoClient() {
        try {
            return new MongoClient(MongoDBConnectionProperties.CONNECTION_URL,
                MongoDBConnectionProperties.CONNECTION_PORT);
        } catch (UnknownHostException e) {
            LOGGER.error("{}", e);
        }
        return null;
    }

}
