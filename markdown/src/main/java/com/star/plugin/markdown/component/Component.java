package com.star.plugin.markdown.component;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanType;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:42
 */
public interface Component {

    /**
     * 正则表达式，用于解析该格式字符串
     */
    String getRegex();

    /**
     * 获取Span样式
     */
    SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanType spanType);

    /**
     * 替换文本
     */
    SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, SpanType spanType);

}
