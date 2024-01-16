package com.restful.booker.testsuite;


import com.restful.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BookingAssertionTest extends TestBase {

    static ValidatableResponse response;

    private static RequestSpecBuilder builder;

    private static RequestSpecification requestSpecification;

    @BeforeClass
    public void inIt1() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
        RestAssured.basePath = "/booking";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", "application/json");
        builder.addQueryParam("$per_page", 20);
        requestSpecification = builder.build();
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        given().log().all()
                .header("Content-Type", "application/json")
                .queryParam("$per_page", 20);

    }

    @Test
    public void test002() {
        response.body("find{it.id ==5914152}.name", equalTo("Anshula Joshi"));
    }
}
