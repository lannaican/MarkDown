package com.star.plugin.markdown;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 9:14
 */
public class MarkDownEditText extends AppCompatEditText {

    public MarkDownEditText(Context context) {
        super(context);
        init();
    }

    public MarkDownEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarkDownEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLineSpacing(0, MarkDown.getProperty().getLineSpacingMultiplier());
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MarkDown.setEditing(MarkDownEditText.this, s);
            }
        });
    }
}
