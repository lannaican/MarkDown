package com.star.plugin.markdown.span.base;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

import androidx.annotation.NonNull;

/**
 * Detail：主题色带有点击效果
 * Author：Stars
 * Create Time：2019/5/7 19:31
 */
public abstract class CharacterClickSpan extends CharacterStyle implements ClickableSpan, MarkDownSpan {

    private boolean pressed;
    private int textColor;
    private int pressBackgroundColor;

    public CharacterClickSpan(int textColor, int pressBackgroundColor) {
        this.textColor = textColor;
        this.pressBackgroundColor = pressBackgroundColor;
    }

    @Override
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint textPaint) {
        textPaint.setColor(textColor);
        textPaint.bgColor = pressed ? pressBackgroundColor: Color.TRANSPARENT;
    }
}
