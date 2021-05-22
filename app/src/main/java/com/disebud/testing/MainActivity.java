package com.disebud.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    public static final String KEY_API = "diljoiad.*&*ANJ*A&*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);


// Cara 1
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(
//                        new Interceptor() {
//                            @Override
//                            public okhttp3.Response intercept(Chain chain) throws IOException {
//                                Request original = chain.request();
//
//                                Request.Builder requestBuilder = original.newBuilder()
//                                        .addHeader("auth",KEY_API)
//                                        .method(original.method(),original.body());
//
//                                Request request = requestBuilder.build();
//                                return chain.proceed(request);
//                            }
//                        }
//                ).build();

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
                .baseUrl("https://cibuni.bisakok.com/Api/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // CARA 3 Header Map
//        HashMap<String, String> headers = new HashMap<String, String>();
//        headers.put("auth","diljoiad.*&*ANJ*A&*");

//        Call<List<Userr>> call = jsonPlaceHolderApi.getUser(headers);

// CARA 2 Header
        Call<List<Userr>> call = jsonPlaceHolderApi.getUser(KEY_API);

        call.enqueue(new Callback<List<Userr>>() {
            @Override
            public void onResponse(Call<List<Userr>> call, Response<List<Userr>> response) {

                Log.v("RES", "CODE " + response.code());

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+ response.code());
                    return;
                }

                List<Userr> users = response.body();

                for (Userr us : users){
                    String content ="";
                    content += "ID : " + us.getIdJamaah()+ "\n";
                    content += "USER ID : " + us.getNama()+ "\n";
                    content += "TITLE : " + us.getRoleId()+ "\n";
                    content += "BODY : " + us.getJk()+ "\n\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Userr>> call, Throwable t) {

            }
        });


        // TESTING API PUBLIC
//        Call<List<Post>> call = jsonPlaceHolderApi.getPost();
//
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//
//                if (!response.isSuccessful()){
//                    textViewResult.setText("Code: "+ response.code());
//                    return;
//                }
//
//
//                List<Post> posts = response.body();
//
//                for (Post post : posts){
//                    String content ="";
//                    content += "ID : " + post.getId()+ "\n";
//                    content += "USER ID : " + post.getUserId()+ "\n";
//                    content += "TITLE : " + post.getTitle()+ "\n";
//                    content += "BODY : " + post.getBody()+ "\n\n";
//
//                    textViewResult.append(content);
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//            }
//        });


    }
}