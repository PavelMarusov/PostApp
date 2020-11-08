package com.example.postapp.ui.presentation.comments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.postapp.R;
import com.example.postapp.adapter.ListCommentsAdapter;
import com.example.postapp.ui.models.CommentsModel;
import com.example.postapp.ui.presentation.post.MainActivity;
import com.example.postapp.util.CustomDialog;
import com.example.postapp.util.CustomDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements CustomDialogFragment.NoticeDialogListener {
    private CommentViewModel mViewModel;
    private int id;
    private TextView title;
    private RecyclerView recyclerView;
    private ListCommentsAdapter adapter;
    private List<CommentsModel> modelList = new ArrayList<>();
    private DialogFragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        init();
        getComingIntent();
        getComments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        newFragment = new CustomDialogFragment();
        newFragment.show(getSupportFragmentManager(), "missiles");
        return super.onOptionsItemSelected(item);
    }

    public void init() {
        title = findViewById(R.id.comment_title);
        recyclerView = findViewById(R.id.comment_recycler);
    }

    public void getComingIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra(MainActivity.ID_KEY, 0);
            mViewModel.getComments(id);
            title.setText(intent.getStringExtra(MainActivity.TITLE_KEY));
        }
    }

    public void getComments() {
        mViewModel.commentsLiveData.observe(this, new Observer<List<CommentsModel>>() {
            @Override
            public void onChanged(List<CommentsModel> commentsModels) {
                modelList.addAll(commentsModels);
                adapter = new ListCommentsAdapter(modelList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onDialogPositiveClick(String s) {
        CommentsModel model =  new CommentsModel();
        model.setEmail(s);
        Log.d("pop","Test =" + s);
    }

    @Override
    public void onDialogNegativeClick(String s) {
        Toast.makeText(this, "Закрыт", Toast.LENGTH_SHORT).show();
    }
}