package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;

import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.span.CharacterSpan;

/**
 * Detail：字体效果
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class CharacterType implements MarkDownType {

    @Override
    public String getRegex() {
        return "(\\*{1,3}).{1,}?\\1";
    }

    @Override
    public void setSpan(Spannable spannable, Item item, boolean edit) {
        MarkDownHelper.setSpan(spannable, new CharacterSpan(getType(item.getText())), item);
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        int type = getType(item.getText());
        return builder.delete(item.getEnd() - type, item.getEnd())
                .delete(item.getStart(), item.getStart() + type);
    }

    private int getType(String text) {
        if (text.length() >=6 && text.startsWith("***") && text.endsWith("***")) return 3;
        if (text.length() >=4 && text.startsWith("**") && text.endsWith("**")) return 2;
        return 1;
    }

}
