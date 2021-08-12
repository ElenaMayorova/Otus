package apiHelpers;

import apiHelpers.pojo.*;

import retrofit2.Call;

import retrofit2.http.*;


public interface APIInterface {

    @GET("user/2")
    Call<User> getUser();

    @POST("register")
    Call<RegisterResponse> crateRegister(@Body RegisterRequest register);

    @PUT("user/2")
    Call<UserUpdateResponse> updateUser (@Body UserUpdate userUpdate);

    @DELETE("user/2")
    Call<User> deleteUser();

}