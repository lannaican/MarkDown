package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.model.Item;

/**
 * Detail：删除线
 * Author：Stars
 * Create Time：2019/5/7 21:08
 */
public class StrikethroughType implements MarkDownType {
    @Override
    public String getRegex() {
        return "~~.{1,}?~~";
    }

    @Override
    public void setSpan(TextView textView, Spannable spannable, Item item, boolean edit) {
        MarkDownHelper.setSpan(spannable, new StrikethroughSpan(), item);
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        return builder.delete(item.getEnd() - 2, item.getEnd())
                .delete(item.getStart(), item.getStart() + 2);
    }
}
