package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.span.LineSpan;

/**
 * 分割线
 * ---
 */
public class LineComponent implements Component {

    private float height;
    private int color;

    public LineComponent(float height, int color) {
        this.height = height;
        this.color = color;
    }

    @Override
    public String getRegex() {
        return "(^|\\n)-{3,}\\n{1}";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end) {
        LineSpan span = new LineSpan(height, color);
        return new SpanInfo(span, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end) {
        return builder;
    }

}
