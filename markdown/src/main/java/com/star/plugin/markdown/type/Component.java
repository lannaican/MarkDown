package com.star.plugin.markdown.type;

import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 6:42
 */
public interface Component {

    /**
     * 每种MarkDown格式实现此容器
     */

    /**
     * 正则表达式，用于解析该格式字符串
     */
    String getRegex();

    /**
     * 获取Span，用于获取该格式显示信息
     */
    SpanInfo getSpanInfo(TextView textView, String item, int start, int end, SpanStyle style);

    /**
     * 替换文本，异步加载时使用
     */
    SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style);

}
