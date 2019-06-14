package com.star.plugin.markdown.span;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

import androidx.annotation.NonNull;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 8:11
 */
public class HSpan extends MetricAffectingSpan implements MarkDownSpan {

    private int color;
    private float size;

    public HSpan(int color, float size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setFakeBoldText(true);
        tp.setColor(color);
        tp.setTextSize(size);
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint tp) {
        tp.setTextSize(size);
    }

}
