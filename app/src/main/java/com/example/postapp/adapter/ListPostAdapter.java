package com.example.postapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postapp.R;
import com.example.postapp.ui.interfaces.IOnItemClick;
import com.example.postapp.ui.models.PostModel;

import java.util.ArrayList;
import java.util.List;

public class ListPostAdapter extends RecyclerView.Adapter<ListPostAdapter.ListPostHolder> {
    private IOnItemClick listener;
    private List<PostModel> posts = new ArrayList<>();

    @NonNull
    @Override
    public ListPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_post, parent, false);
        return new ListPostHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPostHolder holder, int position) {
        if (!posts.isEmpty()) holder.onBind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public void setPosts(List<PostModel> postList) {
        this.posts = postList;
        notifyDataSetChanged();
    }

    public void setListener(IOnItemClick iOnItemClick) {
        this.listener = iOnItemClick;
    }

    class ListPostHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription;

        public ListPostHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.post_title);
            textDescription = itemView.findViewById(R.id.post_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(getAdapterPosition());
                }
            });
        }

        public void onBind(PostModel model) {
            textTitle.setText(model.getBody());
            textDescription.setText(model.getTitle());
        }
    }
}
