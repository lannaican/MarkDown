package com.star.plugin.markdown.listener;

import java.util.ArrayList;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 8:56
 */
public interface OnSpanClickListener {

    void onImageClick(ArrayList<String> images, int index);

    void onMentionClick(String text);

    void onUrlClick(String url);

}
