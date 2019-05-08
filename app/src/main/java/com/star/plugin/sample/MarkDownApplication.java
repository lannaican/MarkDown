package com.star.plugin.sample;

import android.app.Application;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.property.DefaultMarkDownProperty;
import com.star.plugin.markdown.property.ImageLoader;
import com.star.plugin.markdown.type.provider.DefaultMarkDownTypeProvider;

import java.util.concurrent.ExecutionException;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 9:07
 */
public class MarkDownApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MarkDown.init(new DefaultMarkDownTypeProvider(),
                new DefaultMarkDownProperty(this){
                    @Override
                    public ImageLoader getImageLoader() {
                        return new ImageLoader() {
                            @Override
                            public Bitmap getBitmap(String path) {
                                try {
                                    return Glide.with(MarkDownApplication.this)
                                            .asBitmap()
                                            .load(path)
                                            .submit()
                                            .get();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        };
                    }
                });
    }
}
