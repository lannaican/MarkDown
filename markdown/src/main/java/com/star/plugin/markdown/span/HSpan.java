package com.star.plugin.markdown.span;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.LineHeightSpan;
import android.text.style.MetricAffectingSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

import androidx.annotation.NonNull;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 8:11
 */
public class HSpan extends MetricAffectingSpan implements LineHeightSpan, MarkDownSpan {

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

    @Override
    public void chooseHeight(CharSequence charSequence, int i, int i1, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        int lineOffset = (int)(size / 2);
        fontMetricsInt.ascent = fontMetricsInt.ascent + lineOffset;
//        fontMetricsInt.descent = fontMetricsInt.descent + lineOffset;
//        fontMetricsInt.top = fontMetricsInt.top - lineOffset;
//        fontMetricsInt.bottom = fontMetricsInt.bottom + lineOffset;
    }
}
