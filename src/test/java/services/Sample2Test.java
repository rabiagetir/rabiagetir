package services;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class Sample2Test {
    @BeforeClass

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

    @AfterClass
    public void deletePet ()
    {
        int petId = 9013;
        given()
                .log().all().header("api_key", "api-key-value"). // birden fazla olsaydı hash map
                when()
                .delete("https://petstore.swagger.io/v2/pet/"+petId).
                then()
                .statusCode(200)
                .log().all();
    }

}
