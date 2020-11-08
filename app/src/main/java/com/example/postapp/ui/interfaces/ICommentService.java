package com.example.postapp.ui.interfaces;

import com.example.postapp.ui.models.CommentsModel;
import com.example.postapp.ui.models.PostModel;

import java.util.List;

public interface ICommentService {
    void getComment(ICommentCallback callback, int postId);

    interface ICommentCallback  {

        void onSuccess(List<CommentsModel> model);


        void onFailure(Throwable th);
    }
}
