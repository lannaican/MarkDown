package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;

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
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style) {
        return new SpanInfo(new StrikethroughSpan(), start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        return builder.delete(end - 2, end)
                .delete(start, start + 2);
    }

}
