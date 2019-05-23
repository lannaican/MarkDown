package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.span.LineSpan;

/**
 * Detail：分割线
 * Author：Stars
 * Create Time：2019/4/16 9:01
 */
public class LineType implements MarkDownType {

    @Override
    public String getRegex() {
        return "?:(^|\\n)-{3,}\\n{1}";
    }

    @Override
    public void setSpan(TextView textView, Spannable spannable, Item item, boolean edit) {
        if (!edit) {
            MarkDownHelper.setSpan(spannable, new LineSpan(), item);
        }
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        return builder;
    }

}
