package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;

/**
 * Detail：删除线
 * Author：Stars
 * Create Time：2019/5/7 21:08
 */
public class StrikethroughComponent implements Component {

    @Override
    public String getRegex() {
        return "~~.{1,}?~~";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end) {
        return new SpanInfo(new StrikethroughSpan(), start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end) {
        return builder.delete(end - 2, end)
                .delete(start, start + 2);
    }

}
