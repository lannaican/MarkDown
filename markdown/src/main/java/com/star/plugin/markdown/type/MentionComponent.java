package com.star.plugin.markdown.type;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.listener.OnSpanClickListener;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.span.MentionSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 19:38
 */
public class MentionComponent implements Component {

    @Override
    public String getRegex() {
        return "@\\S{1,12}";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, final SpanStyle style) {
        final String name = item.substring(1);
        return new SpanInfo(new MentionSpan() {
            @Override
            public void onSpanClick(View view) {
                OnSpanClickListener clickListener = MarkDown.getProperty().getClickListener();
                if (clickListener != null) {
                    clickListener.onMentionClick(name);
                }
            }
        }, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        return builder;
    }
}
