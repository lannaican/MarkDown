package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 15:33
 */
public class LinkEditSpan implements LeadingMarginSpan, MarkDownSpan {

    private Drawable drawable;

    public LinkEditSpan(Drawable drawable, int size) {
        this.drawable = drawable;
        drawable.setBounds(0, 0, size, size);
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return drawable.getBounds().width() * 3 / 2;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom,
                                  CharSequence text, int start, int end, boolean first, Layout layout) {
        if (first) {
            c.save();
            c.translate(x, top);
            drawable.draw(c);
            c.restore();
        }
    }

}
