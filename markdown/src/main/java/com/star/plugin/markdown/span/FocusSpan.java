package com.star.plugin.markdown.span;

import android.text.style.ForegroundColorSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/8 20:14
 */
public class FocusSpan extends ForegroundColorSpan implements MarkDownSpan {
    public FocusSpan(int color) {
        super(color);
    }
}
