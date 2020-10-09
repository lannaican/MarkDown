package com.star.plugin.markdown.listener;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.TextView;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/28 15:11
 */
public interface OnImageLoadListener {
    Bitmap getBitmap(TextView textView, Context context, String url);
}
