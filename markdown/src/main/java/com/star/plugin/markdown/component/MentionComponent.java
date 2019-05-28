package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.listener.OnMentionClickListener;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.property.MarkDownProperty;
import com.star.plugin.markdown.span.MentionSpan;

import androidx.annotation.NonNull;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 19:38
 */
public class MentionComponent implements Component {

    private OnMentionClickListener mentionClickListener;

    public MentionComponent(@NonNull OnMentionClickListener mentionClickListener) {
        this.mentionClickListener = mentionClickListener;
    }

    @Override
    public String getRegex() {
        return "@[[\\u4e00-\\u9fa5][a-zA-Z]0-9_\\.]{1,12}";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, final SpanStyle style) {
        final String name = item.substring(1);
        MarkDownProperty property =MarkDown.getInstance().getProperty();
        MentionSpan span = new MentionSpan(property.getColor(), property.getPressBackgroundColor()) {
            @Override
            public void onSpanClick(View view) {
                mentionClickListener.onClick(name);
            }
        };
        return new SpanInfo(span, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        return builder;
    }
}
