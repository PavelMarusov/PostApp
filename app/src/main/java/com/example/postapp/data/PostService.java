package com.example.postapp.data;

import android.util.Log;

import com.example.postapp.ui.interfaces.ICommentService;
import com.example.postapp.ui.interfaces.IPostService;
import com.example.postapp.ui.models.CommentsModel;
import com.example.postapp.ui.models.PostModel;
import com.google.gson.internal.GsonBuildConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PostService implements IPostService, ICommentService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    PostApi serviceApi = retrofit.create(PostApi.class);


    @Override
    public void getPosts(IPostCallback callback) {
        Call<List<PostModel>> call = serviceApi.getAllPost();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.isSuccessful()&&response.body()!=null) callback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                callback.onFailure(t);

            }
        });
    }

    @Override
    public void getComment(ICommentCallback callback, int postId) {
        Call<List<CommentsModel>> call = serviceApi.getCommentById(postId);
        call.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                callback.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }


    public interface PostApi {
        @GET("posts/")
        Call <List<PostModel>> getAllPost();

        @GET("comments")
        Call<List<CommentsModel>> getCommentById(@Query("postId") int id);

    }
}
