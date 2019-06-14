package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.span.CodeSpan;

/**
 * Detail：分割线
 * Author：Stars
 * Create Time：2019/4/16 9:01
 */
public class CodeComponent implements Component {

    private int backgroundColor;

    public CodeComponent(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public String getRegex() {
        return "`[\\s\\S]+?`";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style) {
        if (style == SpanStyle.Simple || style == SpanStyle.Editing) {
            return null;
        } else {
            CodeSpan span = new CodeSpan(backgroundColor);
            return new SpanInfo(span, start, end);
        }
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        return builder.delete(end - 1, end).delete(start, start + 1);
    }

}
