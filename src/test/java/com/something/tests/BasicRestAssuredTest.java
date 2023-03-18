package com.something.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class BasicRestAssuredTest {

    @Test
    public void testStatus(){
        Response response = RestAssured.get("https://simple-books-api.glitch.me/status");
        Assert.assertEquals(200, response.getStatusCode());
    }
    @Test
    public void testGetSingleBook(){
        Response response = RestAssured.get("https://simple-books-api.glitch.me/books/1");
        Assert.assertEquals("James Patterson and James O. Born", response.jsonPath().getString("author"));
    }
}
