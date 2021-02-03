package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanType;
import com.star.plugin.markdown.span.QuoteSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class QuoteComponent implements Component {

    private int gapWidth;
    private int lineWidth;
    private int textColor;
    private int lineColor;

    public QuoteComponent(int gapWidth, int lineWidth, int textColor, int lineColor) {
        this.gapWidth = gapWidth;
        this.lineWidth = lineWidth;
        this.textColor = textColor;
        this.lineColor = lineColor;
    }

    @Override
    public String getRegex() {
        return "(^|\\n)> .+\\n?";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanType spanType) {
        if (spanType == SpanType.Normal) {
            QuoteSpan span = new QuoteSpan(gapWidth, lineWidth, textColor, lineColor);
            return new SpanInfo(span, start, end);
        }
        return null;
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, SpanType spanType) {
        return builder.delete(start, start + 2);
    }

}
