package com.star.plugin.markdown.span;

import com.star.plugin.markdown.span.base.CharacterClickSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/21 20:25
 */
public abstract class UrlSpan extends CharacterClickSpan {

    public UrlSpan(int textColor, int pressBackgroundColor) {
        super(textColor, pressBackgroundColor);
    }
}
