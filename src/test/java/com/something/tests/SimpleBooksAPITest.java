package com.something.tests;

import com.something.constants.Constants;
import com.something.pojo.Books;
import com.something.pojo.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.awt.print.Book;

import static org.junit.Assert.assertEquals;

public class SimpleBooksAPITest {

    @Test
    public void testStatus(){
        Response response = RestAssured.when().get(Constants.BASE_URL+Constants.STATUS).thenReturn();
        assertEquals("Status does not match", 200, response.getStatusCode());
        //rest assured way of instantiating the class
        Status status = response.as(Status.class);
        assertEquals("Status does not return correctly", "OK", status.getStatus());
    }

    @Test
    public void testAllBooks(){
        Response response = RestAssured.when().get(Constants.BASE_URL+Constants.BOOKS+"/"+1).thenReturn();
        assertEquals("status codes don't match", 200, response.getStatusCode());

        Books bookIdOne = response.as(Books.class);
        assertEquals("book ids do not match",Integer.valueOf(1), bookIdOne.getId());
        assertEquals("book names don't match", "The Russian", bookIdOne.getName());
        assertEquals("type is not fiction", "fiction", bookIdOne.getType());
        assertEquals("availibility status don't match", true, bookIdOne.getAvailable());
    }
}
