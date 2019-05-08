package com.star.plugin.markdown.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.span.base.MarkDownSpan;

/**
 * Detail：序号
 * Author：Stars
 * Create Time：2019/4/16 8:39
 */
public class IndexSpan implements LeadingMarginSpan, MarkDownSpan {

    private int type;
    private boolean draw;

    public IndexSpan(int type, boolean draw) {
        this.type = type;
        this.draw = draw;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return MarkDown.getProperty().getIndexPadding();
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom,
                                  CharSequence text, int start, int end, boolean first, Layout layout) {
        if (first && draw) {
            String str = type > 0 ? type + "." : "•";
            c.drawText(str, x + dir * MarkDown.getProperty().getIndexPadding() / 3 * 2, baseline, p);
        }
    }
}
