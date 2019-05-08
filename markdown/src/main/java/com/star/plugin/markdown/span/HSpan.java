package com.star.plugin.markdown.span;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.span.base.MarkDownSpan;

import androidx.annotation.NonNull;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 8:11
 */
public class HSpan extends MetricAffectingSpan implements MarkDownSpan {

    private int level;

    public HSpan(int level) {
        this.level = level;
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setFakeBoldText(true);
        tp.setColor(MarkDown.getProperty().getHColor());
        tp.setTextSize(MarkDown.getProperty().getHSize(level));
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint tp) {
        tp.setTextSize(MarkDown.getProperty().getHSize(level));
    }

}
