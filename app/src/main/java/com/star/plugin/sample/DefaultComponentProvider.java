package com.star.plugin.sample;

import com.star.plugin.markdown.component.CharacterComponent;
import com.star.plugin.markdown.component.CodeComponent;
import com.star.plugin.markdown.component.Component;
import com.star.plugin.markdown.component.HComponent;
import com.star.plugin.markdown.component.IndexComponent;
import com.star.plugin.markdown.component.LineComponent;
import com.star.plugin.markdown.component.MentionComponent;
import com.star.plugin.markdown.component.QuoteComponent;
import com.star.plugin.markdown.component.StrikethroughComponent;
import com.star.plugin.markdown.component.provider.ComponentProvider;
import com.star.plugin.markdown.listener.OnMentionClickListener;

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
        components.add(new HComponent(0xFF000000, new float[]{40, 30, 20, 20, 20, 20}));
        components.add(new CharacterComponent());
        components.add(new QuoteComponent(20, 5, 0xFFBBBBBB));
        components.add(new LineComponent(1, 0xFFBBBBBB));
//        components.add(new LinkComponent());
        components.add(new MentionComponent(0xFFFB7299, 0xFFBBBBBB, new OnMentionClickListener() {
            @Override
            public void onClick(String name) {

            }
        }));
        components.add(new StrikethroughComponent());
        components.add(new IndexComponent(40));
        components.add(new CodeComponent(0xFF123456, 0xFFBBBBBB, 10, 10, 10, 0.8F));
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }
}
