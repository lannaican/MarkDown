package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;

import androidx.annotation.NonNull;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：引用
 * Author：Stars
 * Create Time：2019/4/16 8:39
 */
public class QuoteSpan extends ForegroundColorSpan implements LeadingMarginSpan, LineHeightSpan, MarkDownSpan {

    private int gapWidth;
    private int lineWidth;
    private int textColor;
    private int lineColor;

    public QuoteSpan(int gapWidth, int lineWidth, int textColor, int lineColor) {
        super(textColor);
        this.gapWidth = gapWidth;
        this.lineWidth = lineWidth;
        this.textColor = textColor;
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
        int originColor = p.getColor();

        p.setStyle(Paint.Style.FILL);
        p.setColor(lineColor);

        float offsetX = x + dir * lineWidth;
        float offsetY = ((float)bottom - baseline) / 2;
        c.drawRect(x, top - offsetY, offsetX, bottom, p);

        p.setStyle(style);
        p.setColor(originColor);
    }

    @Override
    public void updateDrawState(@NonNull TextPaint textPaint) {
        textPaint.setColor(textColor);
    }

    @Override
    public void chooseHeight(CharSequence charSequence, int i, int i1, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        int lineOffset = ((fontMetricsInt.bottom - fontMetricsInt.top) / 2);
        fontMetricsInt.ascent = fontMetricsInt.ascent - lineOffset;
        fontMetricsInt.descent = fontMetricsInt.descent + lineOffset;
        fontMetricsInt.top = fontMetricsInt.top - lineOffset;
        fontMetricsInt.bottom = fontMetricsInt.bottom + lineOffset;
    }
}
