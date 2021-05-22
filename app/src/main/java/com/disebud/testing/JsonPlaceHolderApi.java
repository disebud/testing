package com.disebud.testing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;

public interface JsonPlaceHolderApi {

// CARA 3
//    @GET("getListName")
//    Call<List<Userr>> getUser(@HeaderMap Map<String,String> headers);

// CARA 2
    @GET("getListName")
    Call<List<Userr>> getUser(@Header("auth") String key);


    @GET("posts")
    Call<List<Post>> getPost();
}
