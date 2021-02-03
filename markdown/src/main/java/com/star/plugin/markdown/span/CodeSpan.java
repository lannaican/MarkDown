package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.style.ReplacementSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * 代码片段
 */
public class CodeSpan extends ReplacementSpan implements MarkDownSpan {

    private int textColor;
    private int padding;
    private int margin;
    private float fontScale;

    private Drawable background;
    private int width;
    private int height;

    public CodeSpan(int textColor, int backgroundColor, int padding, int margin, float radius, float fontScale) {
        this.textColor = textColor;
        this.padding = padding;
        this.margin = margin;
        this.fontScale = fontScale;

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(backgroundColor);
        drawable.setCornerRadius(radius);
        background = drawable;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        height = paint.getFontMetricsInt().descent - paint.getFontMetricsInt().ascent + padding / 2;

        float size = paint.getTextSize();
        paint.setTextSize(size * fontScale);

        width = (int) (paint.measureText(text, start, end) + padding * 2 + margin * 2);

        paint.setTextSize(size);
        return width;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float size = paint.getTextSize();
        paint.setTextSize(size * fontScale);

        Paint.FontMetrics fm = paint.getFontMetrics();
        int drawY = y - (int)(fm.descent - fm.ascent);

        background.setBounds((int) (x + margin), drawY, (int) (x + width - margin), drawY + height);
        background.draw(canvas);

        int color = paint.getColor();
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x + margin + padding, y, paint);
        paint.setColor(color);
        paint.setTextSize(size);
    }
}
