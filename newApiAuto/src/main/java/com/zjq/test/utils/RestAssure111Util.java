package com.zjq.test.utils;

import com.zjq.test.config.Contants;

import static io.restassured.RestAssured.given;

public class RestAssure111Util {

    public static Object RestAssuredPost(String json){
        String resp = given().header("Cookie","")
                .body(json)
                .contentType("application/json")
                .when()
                .post(Contants.BASE_URL)
                .then()
                .log()
                .all()
                .extract()
                .response()
                .asString();
        return resp;
    }

    public void RestAssuredGet(String json){
        String resp = given().header("Cookie","")
                .body(json)
                .contentType("application/json").log().uri().log().params().log().headers()
                .when()
                .get(Contants.BASE_URL)
                .then()
                .log()
                .all()
                .extract()
                .response()
                .asString();
    }

}
