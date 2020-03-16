package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.span.QuoteSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class QuoteComponent implements Component {

    private int gapWidth;
    private int lineWidth;
    private int lineColor;

    public QuoteComponent(int gapWidth, int lineWidth, int lineColor) {
        this.gapWidth = gapWidth;
        this.lineWidth = lineWidth;
        this.lineColor = lineColor;
    }

    @Override
    public String getRegex() {
        return "(^|\\n)> .{1,}?";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end) {
        QuoteSpan span = new QuoteSpan(gapWidth, lineWidth, lineColor);
        return new SpanInfo(span, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end) {
        return builder.delete(start, start + 2);
    }

}
