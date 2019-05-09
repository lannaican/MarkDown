package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.span.HSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class HType implements MarkDownType {

    @Override
    public String getRegex() {
        return "#{1,6} .{1,}";
    }

    @Override
    public void setSpan(TextView textView, Spannable spannable, Item item, boolean edit) {
        MarkDownHelper.setSpan(spannable, new HSpan(getLevel(item)), item);
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        return builder.delete(item.getStart(), item.getStart() + getLevel(item) + 1);
    }

    private int getLevel(Item item) {
        int end = Math.min(item.getEnd() - item.getStart(), 6);
        return item.getText().substring(0, end).lastIndexOf("#") + 1;
    }

}
