package com.star.plugin.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.MarkDownTextView;
import com.star.plugin.markdown.component.Component;

public class MainActivity extends AppCompatActivity {

    private MarkDownTextView textView;
    private EditText editText;

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

            @SuppressLint("CheckResult")
            @Override
            public void afterTextChanged(Editable s) {
                textView.loadAsync(s.toString(), null, null);
            }
        });

        String text="# 测试字符串\n## 标题\n> 引用\n@mention\n+ index\n -index\n普通\n---\n!()[www.baidu.com]";
        for (Component component : new DefaultComponentProvider().getComponents()) {
            long time = System.currentTimeMillis();
            for (int i=0; i<1; i++) {
                MarkDown.getInstance().getItems(component.getRegex(), text);
            }
            long now = System.currentTimeMillis() - time;
            Log.e("time", component.getClass().getSimpleName() + ":" + now);
        }
        editText.setText(text);
    }

}
