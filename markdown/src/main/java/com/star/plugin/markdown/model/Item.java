package com.star.plugin.markdown.model;

import com.star.plugin.markdown.type.MarkDownType;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/15 5:09
 */
public class Item {

    private MarkDownType type;
    private String text;

    private int start, end;

    public Item(MarkDownType type, int start, int end, String text) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.text = text;
    }

    public MarkDownType getType() {
        return type;
    }

    public void setType(MarkDownType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
