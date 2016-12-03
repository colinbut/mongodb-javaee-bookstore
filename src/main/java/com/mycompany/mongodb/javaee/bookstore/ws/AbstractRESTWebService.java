/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.ws;

import javax.ws.rs.core.Response;

public abstract class AbstractRESTWebService {

    protected Response sendResponse(String responseString) {
        return Response.ok(responseString).build();
    }
    
}
