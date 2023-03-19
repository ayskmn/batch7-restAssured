package com.something.tests;

import com.something.constants.Constants;
import com.something.pojo.Books;
import com.something.pojo.FirstBook;
import com.something.pojo.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.awt.print.Book;
import java.util.Optional;

import static com.something.constants.Constants.BASE_URL;
import static com.something.constants.Constants.BOOKS;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;

public class SimpleBooksAPITest {

    @Test
    public void testStatus(){
        Response response = when().get(BASE_URL+Constants.STATUS).thenReturn();
        assertEquals("Status does not match", 200, response.getStatusCode());
        //rest assured way of instantiating the class
        Status status = response.as(Status.class);
        assertEquals("Status does not return correctly", "OK", status.getStatus());
    }

    @Test
    public void testAllBooks(){
        Response response = when()
                .get(BASE_URL+ BOOKS)
                .thenReturn();
        assertEquals("Status does not match", 200, response.getStatusCode());
        Books books = response.as(Books.class);
//        assertEquals(1,books[0].getId());
    }
    @Test
    public void testOneBook(){
        Response response = when()
                .get(BASE_URL+ BOOKS+"/"+1)
                .thenReturn();
        assertEquals("status codes don't match", 200, response.getStatusCode());

        FirstBook firstBook = response.as(FirstBook.class);
        System.out.println(firstBook);
        assertEquals("book ids do not match",Integer.valueOf(1), firstBook.getId());
        assertEquals("book names don't match", "The Russian", firstBook.getName());
        assertEquals("author name is not correct", "James Patterson and James O. Born", firstBook.getAuthor());
        assertEquals("type is not fiction", "fiction", firstBook.getType());
        assertEquals("availibility status don't match", true, firstBook.getAvailable());
    }

    @Test
    public void testListOfBooks(){
        Response response = when()
                .get(BASE_URL+BOOKS)
                .thenReturn();
        Books[] books= response.as(Books[].class);
        assertEquals(6, books.length);
    }

    //to avoid looking at postman for the resources
    @Test
    public void testPretty(){
        String response = given()
                .get(BASE_URL+BOOKS)
                .prettyPrint();
    }
}
