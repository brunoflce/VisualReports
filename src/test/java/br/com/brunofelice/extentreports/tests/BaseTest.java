package br.com.brunofelice.extentreports.tests;

import br.com.brunofelice.extentreports.utils.logs.Log;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;


import java.lang.reflect.Method;

import static br.com.brunofelice.extentreports.utils.extentreports.ExtentTestManager.startTest;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


public class BaseTest {

    @Test(testName ="GET scenario", description = "GET scenario test.")
    public void testGet(Method method) {
        startTest(method.getName(), "GET scenario test.");
        Log.info("Testing GET method");
        get("https://gorest.co.in/public/v2/users").
        then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test(testName = "POST scenario", description = "POST scenario with DELETE method")
    public void testPost(Method method) {

        String jsonBody = "{" +
                "   \"name\":\"Test Automation\",\n" +
                "   \"gender\":\"Male\",\n" +
                "   \"email\":\"testAutomation3@gmail.com\",\n" +
                "   \"status\":\"Active\"\n" +
                "}";

        startTest(method.getName(), "GET scenario test.");
        Log.info("Testando método POST");
        given().header(
                "Authorization",
                "Bearer 0a4562fb401c09007446f88bc4b5c84bf0dd202f312db3741dafd464acf2b2ef").
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                and().
                body(jsonBody).
        when().
                post("https://gorest.co.in//public/v2/users").
        then().
                assertThat().
                statusCode(HttpStatus.SC_CREATED);
    }
}

