package com.star.plugin.markdown.model;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/15 5:09
 */
public class Item {

    public String text;
    public int start, end;

    public Item(int start, int end, String text) {
        this.start = start;
        this.end = end;
        this.text = text;
    }

}
