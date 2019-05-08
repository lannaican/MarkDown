package com.star.plugin.markdown.span;

import android.text.style.StyleSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：字体效果
 * Author：Stars
 * Create Time：2019/4/21 20:23
 */
public class CharacterSpan extends StyleSpan implements MarkDownSpan {

    public CharacterSpan(int type) {
        super(type < 3 ? 3 - type : type);
    }

}
