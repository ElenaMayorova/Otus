import dto.StorePost;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import services.StorePostApi;
import org.junit.jupiter.api.Test;

import static com.sun.javafx.fxml.expression.Expression.equalTo;

public class CreateStorePostTest {

    private StorePostApi storePostApi = new StorePostApi();

    /* Проверяем.что создался StorePost, пришел ответ с кодом  200 и все параметры StorePost соответствуют передаваемым в методе post */
    @Test
    public void checkStore() {
        StorePost storePostFirst = StorePost.builder()

                .id(1)
                .petId(1L)
                .quantity(1L)
                .shipDate("2021-06-26T07:16:44.085+0000")
                .status("plased")
                .complete(true)
                .build();


        Response response = storePostApi.createStore(storePostFirst);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);


        Integer IdExpected = response.jsonPath().get("id");
        Assertions.assertEquals(1, IdExpected);
        Integer petidExpected = response.jsonPath().get("petId");
        Assertions.assertEquals(1, petidExpected);
        Integer quantityExpected = response.jsonPath().get("petId");
        Assertions.assertEquals(1, quantityExpected);
        String shipDateExpected = response.jsonPath().get("shipDate");
        Assertions.assertEquals("2021-06-26T07:16:44.085+0000", shipDateExpected);
        String statusExpected = response.jsonPath().get("status");
        Assertions.assertEquals("plased", statusExpected);
        Boolean completeExpectad = response.jsonPath().get("complete");
        Assertions.assertEquals(true, completeExpectad);
    }

    /*Проверяем,что если не отправить параметр complete StorePost создастся с complete=false */
    @Test
    public void checkStoreWithoutComplete() {
        StorePost storePostSecond = StorePost.builder()

                .id(234432)
                .petId(2L)
                .quantity(2L)
                .shipDate("2021-06-25T07:16:44.085+0000")
                .status("plased")
                .build();

        Response response = storePostApi.createStore(storePostSecond);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

        Boolean completeExpectad = response.jsonPath().get("complete");
        Assertions.assertEquals(false, completeExpectad);
    }

    // отправляем запрос без параметров
    @Test
    public void checkStoreWithoutId() {
        StorePost storePostThird = StorePost.builder()
                .build();

        Response response = storePostApi.createStore(storePostThird);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

        Long IdExpected = response.jsonPath().get("id");
        Assertions.assertNotNull(IdExpected);
    }

}
