package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 9:06
 */
public class LineSpan extends ReplacementSpan implements MarkDownSpan {

    private int color;
    private float height;

    public LineSpan(float height, int color) {
        this.height = height;
        this.color = color;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end,
                       @Nullable Paint.FontMetricsInt fm) {
        return -1;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top,
                     int y, int bottom, @NonNull Paint paint) {
        float center = (top + bottom - ((float)bottom - y) / 2) / 2;
        int color = paint.getColor();
        paint.setColor(this.color);
        canvas.drawRect(0, center - height / 2, canvas.getWidth(), center + height / 2, paint);
        paint.setColor(color);
    }
}
