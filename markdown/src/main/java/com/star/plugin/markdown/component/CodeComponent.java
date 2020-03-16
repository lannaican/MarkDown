package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanType;
import com.star.plugin.markdown.span.CodeSpan;

/**
 * Detail：分割线
 * Author：Stars
 * Create Time：2019/4/16 9:01
 */
public class CodeComponent implements Component {

    private int textColor;
    private int backgroundColor;
    private int padding;
    private int margin;
    private float radius;
    private float fontScale;

    public CodeComponent(int textColor, int backgroundColor, int padding, int margin, float radius, float fontScale) {
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.padding = padding;
        this.margin = margin;
        this.radius = radius;
        this.fontScale = fontScale;
    }

    @Override
    public String getRegex() {
        return "`[\\s\\S]+?`";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanType spanType) {
        if (spanType == SpanType.Normal) {
            CodeSpan span = new CodeSpan(textColor, backgroundColor, padding, margin, radius, fontScale);
            return new SpanInfo(span, start, end);
        }
        return null;
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, SpanType spanType) {
        return builder.delete(end - 1, end).delete(start, start + 1);
    }

}
