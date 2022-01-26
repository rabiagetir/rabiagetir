package services;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class Sample3Test {

    @DataProvider(name = "dataProvider")
    public  Object[][] dataProvider ()
    {
        return new Object[][]{
                {9013, 200}
              // {4, 404}
                //{5,200}
        };
    }
    @Test (dataProvider = "dataProvider")
    public void getPetDetail (int petId, int statusCode)
    {

        given()
                .log().all()
                .when()
                .get("https://petstore.swagger.io/v2/pet/" + petId).
                then()
                .statusCode(statusCode)
                .body("id", equalTo(petId))
                .log().all();

    }
}
