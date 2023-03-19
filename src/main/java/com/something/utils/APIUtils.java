package com.something.utils;

import com.something.constants.Constants;
import com.something.pojo.ApiClientRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIUtils {
    public static String generateToken(){
        ApiClientRequest clientRequest =  new ApiClientRequest("Jane Doe", "clientemail@gmail.com");
        Response response = RestAssured.given().contentType(ContentType.JSON).body(clientRequest).post(Constants.BASE_URL+Constants.TOKEN);
        return response.jsonPath().get("accessToken");
    }
}
