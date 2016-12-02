/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.ws;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This class does exactly the what it says on the name of the class.
 * It activates the REST web services.
 *
 * It activates the REST services on JBoss Wildfly application server
 *
 * The URI path is specified to be used by the REST services
 */
@ApplicationPath("/rest")
public class RESTActivator extends Application {
}
