package services;

import dto.StorePost;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StorePostApi {
    public static final String BASA_URI = "https://petstore.swagger.io/v2/";
    private RequestSpecification spec;
    public static final String STOREPOST = "/store/order";
    public static final String STOREGET = "/store/order/{orderId}";

    public StorePostApi() {
        spec = given()
                .baseUri(BASA_URI)
                .contentType(ContentType.JSON);
    }

    public Response createStore(StorePost storePost) {
        return
                given(spec)
                        .with()
                        .body(storePost)
                        .log().all()
                        .when()
                        .post(STOREPOST);
    }

    public Response getStore(Integer id) {
        return
                given(spec)
                        .pathParam("orderId", id)
                        .log().all()
                        .when()
                        .get(STOREGET);

    }

}
