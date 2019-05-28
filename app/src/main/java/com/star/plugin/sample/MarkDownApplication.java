package com.star.plugin.sample;

import android.app.Application;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.property.DefaultMarkDownProperty;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 9:07
 */
public class MarkDownApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MarkDown.init(new DefaultComponentProvider(),
                new DefaultMarkDownProperty(this));
    }
}
