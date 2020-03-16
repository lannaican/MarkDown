package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.star.plugin.markdown.listener.OnMentionClickListener;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanType;
import com.star.plugin.markdown.span.MentionSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 19:38
 */
public class MentionComponent implements Component {

    private int textColor;
    private int pressBackgroundColor;
    private OnMentionClickListener mentionClickListener;

    public MentionComponent(int textColor, int pressBackgroundColor,
                            @NonNull OnMentionClickListener mentionClickListener) {
        this.textColor = textColor;
        this.pressBackgroundColor = pressBackgroundColor;
        this.mentionClickListener = mentionClickListener;
    }

    @Override
    public String getRegex() {
        return "@[[\\u4e00-\\u9fa5][a-zA-Z]0-9_\\-\\.]{1,12}";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanType spanType) {
        final String name = item.substring(1);
        MentionSpan span = new MentionSpan(textColor, pressBackgroundColor) {
            @Override
            public void onSpanClick(View view) {
                mentionClickListener.onClick(name);
            }
        };
        return new SpanInfo(span, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, SpanType spanType) {
        if (spanType == SpanType.Simple) {
            builder.delete(start, start + 1);
        }
        return builder;
    }

}
