package com.example.postapp.ui.presentation.post;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.postapp.App;
import com.example.postapp.ui.interfaces.IPostService;
import com.example.postapp.ui.models.PostModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    public MutableLiveData<List<PostModel>> postLiveData = new MutableLiveData<>();
    public void getPosts() {
        App.postService.getPosts(new IPostService.IPostCallback() {
            @Override
            public void onSuccess(List<PostModel> model) {
                postLiveData.setValue(model);
            }

            @Override
            public void onFailure(Throwable th) {

            }
        });
    }
}
