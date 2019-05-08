package com.star.plugin.markdown;

import android.text.Spannable;
import android.text.Spanned;

import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 9:27
 */
public class MarkDownHelper {

    /**
     * 清空MarkDownSpan
     */
    public static void clearSpan(Spannable spannable) {
        MarkDownSpan[] spans = spannable.getSpans(0, spannable.length(), MarkDownSpan.class);
        for (MarkDownSpan span : spans) {
            spannable.removeSpan(span);
        }
    }

    /**
     * 设置Span
     */
    public static void setSpan(Spannable spannable, Object span, Item item) {
        spannable.setSpan(span, item.getStart(), item.getEnd(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }

}
