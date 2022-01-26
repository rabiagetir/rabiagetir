package services;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SampleTest {
    @Test
    public void sample (){
        Response response = RestAssured.get("https://petstore.swagger.io/v2/store/inventory");
        System.out.println("getBody: " + response.asString());
        System.out.println("getBody: " + response.getBody().asString());
        System.out.println("statusCode: " + response.statusCode());
        System.out.println("getStatusCode: " + response.getStatusCode());
        System.out.println("getHeader: " + response.getHeader("Content-Type"));
        System.out.println("getTime: " + response.getTime());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getInventory ()
    {
        Matcher matcher;
        given()
                .log().all().
                when()
                .get("https://petstore.swagger.io/v2/store/inventory").
                then()
                .statusCode(200).
                time(lessThan(2000L))
                .log().all();
    }
    @Test
    public void getPetDetail ()
    {
        int petId = 9013;
        int CId = 28;
        String Cname = "yem";
        given()
                .log().all()
                .when()
                .get("https://petstore.swagger.io/v2/pet/" + petId).
                then()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("status", startsWith("av"))
                .body("category.id", equalTo(CId))
                .body("tags[0].name", equalTo(Cname))
                .log().all();

    }

    @Test
    public void postCreatePet ()
    {
        String postData = "{\n" +
                "  \"id\": 9013,\n" +
                "  \"category\": {\n" +
                "    \"id\": 28,\n" +
                "    \"name\": \"kuş\"\n" +
                "  },\n" +
                "  \"name\": \"tahin\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"http://cdn-dev.getir.com/misc/60dc5a0de3eb4435238b8b39_tr.jpeg?v=1625055090088\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 43,\n" +
                "      \"name\": \"yem\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .body(postData)
                .contentType(ContentType.JSON)
                .log().all().
                when()
                .post("https://petstore.swagger.io/v2/pet").
                then()
                .statusCode(200)
                .log().all();

    }

    // model ile post - sipairş isteği yolladık

    @Test
    public void postOrder ()
    {
        OrderRequest orderRequest = new OrderRequest(34, 1093, 1, "2022-01-25T18:29:08.371Z", "placed", true);

        String requestBody = new Gson().toJson(orderRequest);
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(requestBody).
                when()
                .post("https://petstore.swagger.io/v2/store/order").
                then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public  void  postUpdatePet ()
    {
        int petId = 5;
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", "doggie");
        queryParams.put("status", "available");

        given()
                .log().all().queryParams(queryParams).
                when()
                .post("https://petstore.swagger.io/v2/pet/"+petId).
                then()
                .statusCode(200)
                .log().all();
    }



}
