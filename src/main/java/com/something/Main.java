package com.something;

import com.something.utils.APIUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Main {
    public static void main(String[] args){
        Response response = RestAssured.get("https://simple-books-api.glitch.me/books/1");
        System.out.println(response.body().asString());
        System.out.println(response.statusCode());
        System.out.println(response.headers());
        System.out.println(response.jsonPath().getString("name"));
        System.out.println(response.jsonPath().getString("author"));

//        String token = APIUtils.generateToken();
//        System.out.println(token);

    }
}
