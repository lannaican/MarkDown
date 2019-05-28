package com.star.plugin.markdown.type.provider;

import android.graphics.Bitmap;

import com.star.plugin.markdown.type.CharacterComponent;
import com.star.plugin.markdown.type.HComponent;
import com.star.plugin.markdown.type.LinkComponent;
import com.star.plugin.markdown.type.IndexComponent;
import com.star.plugin.markdown.type.LineComponent;
import com.star.plugin.markdown.type.Component;
import com.star.plugin.markdown.type.MentionComponent;
import com.star.plugin.markdown.type.QuoteComponent;
import com.star.plugin.markdown.type.StrikethroughComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 9:28
 */
public class DefaultComponentProvider implements ComponentProvider {

    private List<Component> components;

    public DefaultComponentProvider() {
        components = new ArrayList<>();
        components.add(new HComponent());
        components.add(new CharacterComponent());
        components.add(new QuoteComponent());
        components.add(new LineComponent());
        components.add(new LinkComponent() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void onImageClick(ArrayList<String> images, int index) {

            }

            @Override
            public void onUrlClick(String url) {

            }
        });
        components.add(new MentionComponent() {
            @Override
            public void onMentionClick(String name) {

            }
        });
        components.add(new StrikethroughComponent());
        components.add(new IndexComponent());
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }
}
