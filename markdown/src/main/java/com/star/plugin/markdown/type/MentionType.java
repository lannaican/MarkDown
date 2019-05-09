package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.listener.OnSpanClickListener;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.span.MentionSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 19:38
 */
public class MentionType implements MarkDownType {

    @Override
    public String getRegex() {
        return "@[\\p{L}0-9_-]{1,9}";
    }

    @Override
    public void setSpan(TextView textView, Spannable spannable, Item item, boolean edit) {
        String name = item.getText().substring(1);
        MarkDownHelper.setSpan(spannable, new MentionSpan() {
            @Override
            public void onSpanClick(View view) {
                if (!edit) {
                    OnSpanClickListener clickListener = MarkDown.getProperty().getClickListener();
                    if (clickListener != null) {
                        clickListener.onMentionClick(name);
                    }
                }
            }
        }, item);
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        return builder;
    }
}
