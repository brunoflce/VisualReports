package br.com.brunofelice.extentreports.tests;

import br.com.brunofelice.extentreports.utils.logs.Log;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;



import java.lang.reflect.Method;

import static br.com.brunofelice.extentreports.utils.extentreports.ExtentTestManager.startTest;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;


public class BaseTest {

    @BeforeTest
    public void beforeTest() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
    }

    @Test(testName ="GET scenario", description = "GET scenario test.", groups = "gettests")
    public void testGet(Method method) {

        startTest(method.getName(), "GET scenario test.");
        Log.info("Testing GET method");
        get("users?page=1").
        then().
                assertThat().
                body(matchesJsonSchemaInClasspath("schema.json")).
                statusCode(HttpStatus.SC_OK).
                body("[7].name", equalTo("Jaya Mehrotra")).


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
                post("users").
        then().
                assertThat().
                statusCode(HttpStatus.SC_CREATED);

    }
}

