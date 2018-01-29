package com.example.vadym.moviedirectoryapp.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vadym.moviedirectoryapp.R;

/**
 * Created by azazellj on 1/28/18.
 */

public class MovieSearchDialog extends AlertDialog implements View.OnClickListener {

    private EditText searchEditText;
    private Button submitBtn;

    @Nullable
    private OnMovieSearchListener listener;

    public void setListener(@Nullable OnMovieSearchListener listener) {
        this.listener = listener;
    }

    protected MovieSearchDialog(@NonNull Context context) {
        super(context);

        initViews();
    }

    @SuppressLint("InflateParams")
    private void initViews() {
        View rootView = getLayoutInflater().inflate(R.layout.dialog_view, null);
        searchEditText = rootView.findViewById(R.id.searchEdt);
        submitBtn = rootView.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(this);

        setView(rootView);
    }

    @Override
    public void onClick(View v) {
        String text = searchEditText.getText().toString();

        if (listener != null && !TextUtils.isEmpty(text)) {
            listener.startSearch(text);
        }

        dismiss();
    }

    public interface OnMovieSearchListener {
        void startSearch(String searchString);
    }
}
