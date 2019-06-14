package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：引用
 * Author：Stars
 * Create Time：2019/4/16 8:39
 */
public class QuoteSpan implements LeadingMarginSpan, MarkDownSpan {

    private int gapWidth;
    private int lineWidth;
    private int lineColor;

    public QuoteSpan(int gapWidth, int lineWidth, int lineColor) {
        this.gapWidth = gapWidth;
        this.lineWidth = lineWidth;
        this.lineColor = lineColor;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return gapWidth + lineWidth;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom,
                                  CharSequence text, int start, int end, boolean first, Layout layout) {
        Paint.Style style = p.getStyle();
        int color = p.getColor();

        p.setStyle(Paint.Style.FILL);
        p.setColor(lineColor);

        float offsetX = x + dir * lineWidth;
        float offsetY = ((float)bottom - baseline) / 2;
        c.drawRect(x, top - offsetY, offsetX, bottom, p);

        p.setStyle(style);
        p.setColor(color);
    }
}
