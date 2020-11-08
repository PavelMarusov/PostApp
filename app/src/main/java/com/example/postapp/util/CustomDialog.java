package com.example.postapp.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.postapp.R;
import com.example.postapp.ui.models.CommentsModel;

import java.util.List;

public class CustomDialog extends Dialog implements View.OnClickListener {
private  EditText name, comment;
private Button add;
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        name = findViewById(R.id.name_edit);
        comment = findViewById(R.id.comment_edit);
        add.setOnClickListener(this::onClick);
    }
     public void saveComment(List<CommentsModel> model){

      }


    @Override
    public void onClick(View view) {
       cancel();
    }
}
