package com.example.postapp.ui.presentation.post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;

import com.example.postapp.R;
import com.example.postapp.adapter.ListPostAdapter;
import com.example.postapp.ui.interfaces.IOnItemClick;
import com.example.postapp.ui.models.PostModel;
import com.example.postapp.ui.presentation.comments.CommentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mViewModel;
    private List<PostModel> modelList= new ArrayList<>();
    private ListPostAdapter adapter;
    private RecyclerView recyclerView;
    public static final String ID_KEY = "com.example.postapp.idKey";
    public static final String TITLE_KEY = "com.example.postapp.titleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getPosts();
        initRecyclerView();
        getData();
        initListeners();
    }
    public void initRecyclerView(){
        recyclerView = findViewById(R.id.recycler);
        adapter = new ListPostAdapter();
        recyclerView.setAdapter(adapter);

    }
    public void getData(){
        mViewModel.postLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                if (postModels!=null){
                modelList.addAll(postModels);
                adapter.setPosts(modelList);}
            }
        });
    }
    public void initListeners(){
        adapter.setListener(new IOnItemClick() {
            @Override
            public void OnItemClick(int position) {
                Intent intent =  new Intent(MainActivity.this, CommentActivity.class);
                intent.putExtra(ID_KEY,modelList.get(position).getId());
                intent.putExtra(TITLE_KEY,modelList.get(position).getBody());
                startActivity(intent);
            }
        });
    }
}