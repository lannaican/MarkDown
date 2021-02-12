package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.span.QuoteSpan;

/**
 * 引用
 * >
 */
public class QuoteComponent implements Component {

    private int gapWidth;
    private int lineOffset;
    private int lineWidth;
    private int textColor;
    private int lineColor;

    public QuoteComponent(int gapWidth, int lineOffset, int lineWidth, int textColor, int lineColor) {
        this.gapWidth = gapWidth;
        this.lineOffset = lineOffset;
        this.lineWidth = lineWidth;
        this.textColor = textColor;
        this.lineColor = lineColor;
    }

    @Override
    public String getRegex() {
        return "(^|\\n)> .+\\n?";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end) {
        QuoteSpan span = new QuoteSpan(gapWidth, lineOffset, lineWidth, textColor, lineColor);
        return new SpanInfo(span, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end) {
        return builder.delete(start, start + 2);
    }

}
