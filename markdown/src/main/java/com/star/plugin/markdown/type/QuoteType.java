package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.span.QuoteSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class QuoteType implements MarkDownType {

    @Override
    public String getRegex() {
        return "?:(^|\\n)> .{1,}\\n?";
    }

    @Override
    public void setSpan(TextView textView, Spannable spannable, Item item, boolean edit) {
        MarkDownHelper.setSpan(spannable, new QuoteSpan(), item);
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        return builder.delete(item.getStart(), item.getStart() + 2);
    }

}
