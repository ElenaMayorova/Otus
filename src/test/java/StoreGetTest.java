import dto.StorePost;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.StorePostApi;
import  static  org.hamcrest.Matchers.equalTo;

public class StoreGetTest {
    private StorePostApi storePostApi = new StorePostApi();

    /* Получение Store по существующему id  */
    @Test
    public void getStoreById() {
        Integer id = 234432;
        Response response = storePostApi.getStore(id);
        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("id", equalTo(id))
                .body("status", equalTo("plased"))
                .body("shipDate", equalTo("2021-06-25T07:16:44.085+0000"))
                .body("complete", equalTo(false))
                .body("petId", equalTo(2))
                .body("quantity", equalTo(2));
    }

    /* Получение Store по не существующему id  */
    @Test
    public void getStoreByIdNotexist() {
        Integer id = 234432000;
        Response response = storePostApi.getStore(id);
        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }


}
