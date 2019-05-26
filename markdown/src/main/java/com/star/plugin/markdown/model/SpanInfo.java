package com.star.plugin.markdown.model;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/26 19:05
 */
public class SpanInfo {

    private Object span;
    private int start;
    private int end;

    public SpanInfo(Object span, int start, int end) {
        this.span = span;
        this.start = start;
        this.end = end;
    }

    public Object getSpan() {
        return span;
    }

    public void setSpan(Object span) {
        this.span = span;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
