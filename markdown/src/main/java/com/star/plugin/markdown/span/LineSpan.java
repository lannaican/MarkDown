package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.span.base.MarkDownSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 9:06
 */
public class LineSpan extends ReplacementSpan implements MarkDownSpan {

    public LineSpan() {

    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end,
                       @Nullable Paint.FontMetricsInt fm) {
        return -1;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top,
                     int y, int bottom, @NonNull Paint paint) {
        int center = (top + bottom - (bottom - y) / 2) / 2;
        int color = paint.getColor();
        paint.setColor(MarkDown.getProperty().getLineColor());
        int height = MarkDown.getProperty().getLineHeight();
        canvas.drawRect(0, center - height / 2, canvas.getWidth(), center + height / 2, paint);
        paint.setColor(color);
    }
}
