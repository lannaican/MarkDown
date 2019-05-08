package com.star.plugin.markdown.span.base;

import android.view.View;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/4/16 14:08
 */
public interface ClickableSpan {

    void onSpanClick(View view);

    void setPressed(boolean pressed);
}
