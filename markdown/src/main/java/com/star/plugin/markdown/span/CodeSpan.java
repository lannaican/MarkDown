package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.style.ReplacementSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/6/14 14:27
 */
public class CodeSpan extends ReplacementSpan implements MarkDownSpan {

    private static final float PADDING_HORIZONTAL = 16;
    private static final float PADDING_VERTICAL = 2;
    private static final float MARGIN = 8;
    private static final float TEXT_SIZE_SCALE = 0.92f;

    private Drawable background;
    private int width;
    private int height;

    public CodeSpan(int backgroundColor) {
        ColorDrawable d = new ColorDrawable();
        d.setColor(backgroundColor);
        background = d;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        height = paint.getFontMetricsInt().descent - paint.getFontMetricsInt().ascent;

        float size = paint.getTextSize();
        paint.setTextSize(size * TEXT_SIZE_SCALE);
        paint.setTypeface(Typeface.MONOSPACE);

        width = (int) (paint.measureText(text, start, end) + PADDING_HORIZONTAL * 2 + MARGIN * 2);
        if (fm != null) {
            fm.top -= PADDING_VERTICAL;
            fm.bottom += PADDING_VERTICAL;
        }

        paint.setTextSize(size);
        return width;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float size = paint.getTextSize();
        paint.setTextSize(size * TEXT_SIZE_SCALE);
        paint.setTypeface(Typeface.MONOSPACE);

        background.setBounds((int) (x + MARGIN), (int) (top - PADDING_VERTICAL), (int) (x + width - MARGIN), (int) (top + height + PADDING_VERTICAL));
        background.draw(canvas);

        int color = paint.getColor();
        canvas.drawText(text, start, end, x + MARGIN + PADDING_HORIZONTAL, y - height * (1 - TEXT_SIZE_SCALE) * 0.5f, paint);
        paint.setColor(color);

        paint.setTextSize(size);
    }
}
