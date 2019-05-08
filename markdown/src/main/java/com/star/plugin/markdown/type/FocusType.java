package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.model.Item;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/8 10:26
 */
public class FocusType implements MarkDownType {
    @Override
    public String getRegex() {
        return "#{2}.{1,}?#{2}";
    }

    @Override
    public void setSpan(Spannable spannable, Item item, boolean edit) {
        MarkDownHelper.setSpan(spannable, new ForegroundColorSpan(MarkDown.getProperty().getColor()), item);
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        return builder.delete(item.getEnd() - 2, item.getEnd())
                .delete(item.getStart(), item.getStart() + 2);
    }
}
