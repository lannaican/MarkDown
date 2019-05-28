package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.property.MarkDownProperty;
import com.star.plugin.markdown.span.QuoteSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class QuoteComponent implements Component {

    @Override
    public String getRegex() {
        return "(^|\\n)> .{1,}?";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style) {
        if (style == SpanStyle.Simple) {
            return null;
        } else {
            MarkDownProperty property = MarkDown.getInstance().getProperty();
            QuoteSpan span = new QuoteSpan(
                    property.getQuoteGapWidth(),
                    property.getQuoteWidth(),
                    property.getQuoteColor());
            return new SpanInfo(span, start, end);
        }
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        return builder.delete(start, start + 2);
    }

}
