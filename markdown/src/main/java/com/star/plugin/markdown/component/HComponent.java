package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.span.HSpan;

/**
 * H1 - H6
 * # - ######
 */
public class HComponent implements Component {

    private int textColor;
    private float[] textSizes;

    public HComponent(int textColor, float[] sizes) {
        this.textColor = textColor;
        this.textSizes = sizes;
    }

    @Override
    public String getRegex() {
        return "(^|\\n)#{1,6} .+";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end) {
        int level = getLevel(item);
        HSpan span = new HSpan(textColor, textSizes[level - 1]);
        return new SpanInfo(span, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end) {
        return builder.delete(start, start + getLevel(item) + 1);
    }

    /**
     * 获取标题等级
     */
    private int getLevel(String text) {
        return text.indexOf(" ");
    }

}
