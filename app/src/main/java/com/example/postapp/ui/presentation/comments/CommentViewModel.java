package com.example.postapp.ui.presentation.comments;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.postapp.App;
import com.example.postapp.ui.interfaces.ICommentService;
import com.example.postapp.ui.models.CommentsModel;

import java.util.List;

public class CommentViewModel extends ViewModel {
    public MutableLiveData<List<CommentsModel>> commentsLiveData =  new MutableLiveData<>();
    public void getComments(int id){
        App.postService.getComment(new ICommentService.ICommentCallback() {
            @Override
            public void onSuccess(List<CommentsModel> model) {
                commentsLiveData.setValue(model);
            }

            @Override
            public void onFailure(Throwable th) {
            }
        },id);
    }

}
