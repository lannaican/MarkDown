package com.star.plugin.markdown.type;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.span.LineSpan;

/**
 * Detail：分割线
 * Author：Stars
 * Create Time：2019/4/16 9:01
 */
public class LineComponent implements Component {

    @Override
    public String getRegex() {
        return "(^|\\n)-{3,}\\n{1}";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style) {
        if (style == SpanStyle.Editing) {
            return null;
        } else {
            return new SpanInfo(new LineSpan(), start, end);
        }
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        return builder;
    }

}
