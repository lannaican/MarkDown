package com.star.plugin.markdown.listener;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/28 15:11
 */
public interface OnImageLoadListener {
    Bitmap getBitmap(Context context, String url);
}
