package com.example.postapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.postapp.R;
import com.example.postapp.ui.models.CommentsModel;
import com.example.postapp.ui.presentation.comments.CommentActivity;
import com.example.postapp.ui.presentation.post.MainActivity;

public class CustomDialogFragment extends DialogFragment {
    NoticeDialogListener mListener;
    EditText email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog, container, false);
        email = view.findViewById(R.id.mail_edit);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null))

                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("pop","Test =" + email.getText().toString());
                        mListener.onDialogPositiveClick(email.getText().toString());

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    public interface NoticeDialogListener {
        void onDialogPositiveClick(String s);

        void onDialogNegativeClick(String s);
    }

    public void setmListener(NoticeDialogListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
