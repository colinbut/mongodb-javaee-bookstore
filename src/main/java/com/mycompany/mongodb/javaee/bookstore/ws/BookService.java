/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.mongodb.javaee.bookstore.ws;

import com.mycompany.mongodb.javaee.bookstore.BookStore;
import com.mycompany.mongodb.javaee.bookstore.bean.Book;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bookstore")
public class BookService extends AbstractRESTWebService {

    @Inject
    private BookStore bookStore;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> query() {
        return bookStore.query();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buyBook(Book bookToBuy) {
        Book book = bookStore.checkAvailability(bookToBuy);

        if (book == null) {
            return sendResponse("Book not found sorry!");
        }

        if (book.getCopies() > 0 ) {
            bookStore.buy(book);
            return sendResponse("Book purchased!");
        } else {
            return sendResponse("No more copies available sorry!");
        }

    }
}
