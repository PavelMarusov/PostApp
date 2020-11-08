package com.example.postapp.ui.interfaces;

import com.example.postapp.ui.models.PostModel;

import java.util.List;

public interface IPostService {
    void getPosts(IPostCallback callback);

    interface IPostCallback  {

        void onSuccess(List<PostModel> postList);


        void onFailure(Throwable th);
    }
}
