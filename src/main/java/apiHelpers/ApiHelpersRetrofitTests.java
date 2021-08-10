package apiHelpers;

import apiHelpers.pojo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;

import java.io.IOException;

@SpringBootTest
class ApiHelpersRetrofitTests {

    APIInterface service = APIClientHelper.getEntity().create(APIInterface.class);

    @Test
    @DisplayName("Get user")
    void getUseTest() throws IOException {
        Response<User> responseCreateUser;
        User user;
        responseCreateUser = service.getUser().execute();
        user = responseCreateUser.body();
        System.out.println(user.getData());
        System.out.println(user.getSupport());

        if (responseCreateUser.isSuccessful()) {
            System.out.println("Response SUCCESS");
        } else {
            System.out.println("Response ERROR");
        }
        System.out.println(responseCreateUser.body());
        Assertions.assertEquals(200, responseCreateUser.code());
        Assertions.assertEquals(2, responseCreateUser.body().getData().getId());
        Assertions.assertEquals("janet.weaver@reqres.in", responseCreateUser.body().getData().getEmail());
        Assertions.assertEquals("Janet", responseCreateUser.body().getData().getFirstName());
        Assertions.assertEquals("Weaver", responseCreateUser.body().getData().getLastName());
        Assertions.assertEquals("https://reqres.in/img/faces/2-image.jpg", responseCreateUser.body().getData().getAvatar());
        Assertions.assertEquals("https://reqres.in/#support-heading", responseCreateUser.body().getSupport().getUrl());
        Assertions.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", responseCreateUser.body().getSupport().getText());

    }

    @Test
    @DisplayName("Delete user")
    void deleteUserTest() {
        Response<User> response;

        try {
            response = service.deleteUser().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert response.isSuccessful();

        if (response.isSuccessful()) {
            System.out.println("Response SUCCESS");
        } else {
            System.out.println("Response ERROR");
        }
        Assertions.assertEquals(204, response.code());
    }

    @Test
    @DisplayName("Post register")
    void postRegisterTest() throws IOException {
        Response<RegisterResponse> responseCreateRegister;
        RegisterResponse registerResponse;

        System.out.println(getRequestBody());
        responseCreateRegister = service.crateRegister(getRequestBody()).execute();

        registerResponse = responseCreateRegister.body();
        System.out.println(registerResponse.getId());
        System.out.println(registerResponse.getToken());

        if (responseCreateRegister.isSuccessful()) {
            System.out.println("Response SUCCESS");
        } else {
            System.out.println("Response ERROR");
        }
        System.out.println(responseCreateRegister.body());
        Assertions.assertEquals(200, responseCreateRegister.code());
        Assertions.assertEquals(4, responseCreateRegister.body().getId());
        Assertions.assertEquals("QpwL5tke4Pnpja7X4", responseCreateRegister.body().getToken());
    }

    public RegisterRequest getRequestBody() {
        RegisterRequest requestNewRegister = new RegisterRequest();
        requestNewRegister.setEmail("eve.holt@reqres.in");
        requestNewRegister.setPassword("pistol");

        return requestNewRegister;
    }


    @Test
    @DisplayName("UpdateUser")
    void putUpdateUserTest() throws IOException {
        Response<UserUpdateResponse> responseUpdateUser;
        UserUpdateResponse userUpdateResponse;

        System.out.println(getRequestUpdateUser());
        responseUpdateUser = service.updateUser(getRequestUpdateUser()).execute();

        userUpdateResponse = responseUpdateUser.body();
        System.out.println(userUpdateResponse.getJob());
        System.out.println(userUpdateResponse.getName());

        if (responseUpdateUser.isSuccessful()) {
            System.out.println("Response SUCCESS");
        } else {
            System.out.println("Response ERROR");
        }
        System.out.println(responseUpdateUser.body());
        Assertions.assertEquals(200, responseUpdateUser.code());
        Assertions.assertEquals("morpheus", responseUpdateUser.body().getName());
        Assertions.assertEquals("zion resident", responseUpdateUser.body().getJob());
    }

    public UserUpdate getRequestUpdateUser() {
        UserUpdate updateUser = new UserUpdate();
        updateUser.setJob("zion resident");
        updateUser.setName("morpheus");

        return updateUser;
    }
}