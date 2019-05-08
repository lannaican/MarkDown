package com.star.plugin.sample;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.MarkDownEditText;
import com.star.plugin.markdown.MarkDownTextView;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private MarkDownTextView textView;
    private MarkDownEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressWarnings("ResultOfMethodCallIgnored")
            @SuppressLint("CheckResult")
            @Override
            public void afterTextChanged(Editable s) {
                textView.setMarkDown(s.toString());
            }
        });
    }

}
