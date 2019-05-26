package com.star.plugin.markdown.type;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.span.CharacterSpan;

/**
 * Detail：字体效果 - 粗体/斜体/粗斜体
 * Author：Stars
 * Create Time：2019/4/16 6:46
 */
public class CharacterComponent implements Component {

    @Override
    public String getRegex() {
        return "(\\*{1,3}).{1,}?\\1";
    }

    @Override
    public SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style) {
        if (style == SpanStyle.Simple) {
            return null;
        } else {
            return new SpanInfo(new CharacterSpan(getType(item)), start, end);
        }
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        int type = getType(item);
        if (style == ReplaceStyle.Display) {
            return builder.delete(end - type, end)
                    .delete(start, start + type);
        } else if (style == ReplaceStyle.Origin) {
            return builder.delete(end - type, end)
                    .delete(start, start + type);
        }
        return null;
    }


    /**
     * 获取效果类型
     */
    private int getType(String text) {
        if (text.length() >=6 && text.startsWith("***") && text.endsWith("***")) return 3;
        if (text.length() >=4 && text.startsWith("**") && text.endsWith("**")) return 2;
        return 1;
    }

}
