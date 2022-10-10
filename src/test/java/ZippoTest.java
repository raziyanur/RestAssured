import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ZippoTest {

    @Test
    public void test(){

        given()

                     // hazirlik islemlerini yapacagiz( token, send body, parametreler)
                .when()
                     // link i ve metodu veriyoruz

                .then()
                     // assertion ve verileriele alma extract
        ;
    }

    @Test
    public void statusCodeTest(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)

        ;
    }


    @Test
    public void contenTypeTest(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)

        ;
    }

    @Test
    public void checkStateInResponeBody(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("country",equalTo("United States"))
                .statusCode(200)


        ;
    }

    @Test
    public void bodyJsonPathTest2(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .body("places[0].state",equalTo("California" +
                        ""))
                .statusCode(200)


        ;
    }

    @Test
    public void extractingJsonPathInt(){

        int limit=
        given()

                .when()
                .get("https://gorest.co.in/public/v1/users")

                .then()
                .log().body()
                .statusCode(200)
                .extract().path("meta.pagination.limit")
        ;
        System.out.println("limit = " + limit);
        Assert.assertEquals(limit,10,"test sonuc");
    }

    @Test
    public void extractingJsonPathInt2(){

        int id=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data[2].id")
                ;
        System.out.println("id = " + id);

    }

    @Test
    public void extractingJsonPathIntList(){

        List<Integer> idler=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data.id")
                ;
        System.out.println("idler = " + idler);
        Assert.assertTrue(idler.contains(3045));
    }

    @Test
    public void extractingJsonPathStringList(){

        List<String > names=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data.name")
                ;
        System.out.println("names = " + names);
        Assert.assertTrue(names.contains("Datta Achari"));
    }

    @Test
    public void extractingJsonPathStringList2(){

        Response body=
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().response() // butun body alindi
                ;
       List<Integer> idler=body.path("data.id");
        List<String > names=body.path("data.name");
        int limit=body.path("meta.pagination.limit");

        System.out.println("limit = " + limit);
        System.out.println("names = " + names);
        System.out.println("idler = " + idler);
    }





}
