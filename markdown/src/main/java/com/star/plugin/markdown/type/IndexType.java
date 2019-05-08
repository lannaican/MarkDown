package com.star.plugin.markdown.type;

import android.text.Spannable;
import android.text.SpannableStringBuilder;

import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.span.IndexSpan;
import com.star.plugin.markdown.span.QuoteSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class IndexType implements MarkDownType {

    @Override
    public String getRegex() {
        return "(([1-9]\\.)|\\+ |\\* |\\- ).*";
    }

    @Override
    public void setSpan(Spannable spannable, Item item, boolean edit) {
        int type = getType(item.getText());
        MarkDownHelper.setSpan(spannable, new IndexSpan(type, !edit), item);
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        return builder.delete(item.getStart(), item.getStart() + 2);
    }

    private int getType(String text){
        try {
            return Integer.parseInt(text.substring(0, 1));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
