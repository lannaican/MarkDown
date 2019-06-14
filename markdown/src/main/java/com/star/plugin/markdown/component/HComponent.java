package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.span.HSpan;

/**
 * Detail：H1 - H6
 * Author：Stars
 * Create Time：2019/4/16 6:46
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
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style) {
        if (style == SpanStyle.Simple) {
            return null;
        } else {
            int level = getLevel(item, start, end);
            HSpan span = new HSpan(textColor, textSizes[level]);
            return new SpanInfo(span, start, end);
        }
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        return builder.delete(start, start + getLevel(item, start, end) + 1);
    }

    /**
     * 获取标题等级
     */
    private int getLevel(String text, int start, int end) {
        end = Math.min(end - start, 6);
        String startStr = text.substring(0, end);
        if (startStr.startsWith("######")) {
            return 6;
        } else if (startStr.startsWith("#####")) {
            return 5;
        } else if (startStr.startsWith("####")) {
            return 4;
        } else if (startStr.startsWith("###")) {
            return 3;
        } else if (startStr.startsWith("##")) {
            return 2;
        } else {
            return 1;
        }
    }

}
