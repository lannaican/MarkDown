package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.span.IndexSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class IndexComponent implements Component {

    @Override
    public String getRegex() {
        return "(^|\\n)(([1-9]\\. )|\\+ |\\* |\\- ).*";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style) {
        boolean startWithLine = startWithLine(item);
        if (startWithLine) {
            item = item.substring(1);
            start++;
        }
        int type = getType(item);
        IndexSpan span = new IndexSpan(
                type,
                MarkDown.getInstance().getProperty().getIndexPadding(),
                style != SpanStyle.Editing);
        return new SpanInfo(span, start, end);
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        boolean startWithLine = startWithLine(item);
        if (startWithLine) {
            start++;
        }
        return builder.delete(start, start + 2);
    }

    //首字符为换行
    private boolean startWithLine(String text) {
        return text.startsWith("\n");
    }

    private int getType(String text){
        try {
            return Integer.parseInt(text.substring(0, 1));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
