package com.example.postapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.postapp.R;
import com.example.postapp.ui.models.CommentsModel;

import java.util.List;

public class ListCommentsAdapter extends RecyclerView.Adapter<ListCommentsAdapter.CommentHolder> {
    private List<CommentsModel> list;

    public ListCommentsAdapter(List<CommentsModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_comments, parent, false);
        return new CommentHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CommentHolder extends RecyclerView.ViewHolder {
        TextView commName, commBody;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            commName = itemView.findViewById(R.id.comm_name);
            commBody = itemView.findViewById(R.id.comm_body);
        }

        public void onBind(CommentsModel model) {
            commName.setText(model.getName());
            commBody.setText(model.getBody());
        }
    }
}
