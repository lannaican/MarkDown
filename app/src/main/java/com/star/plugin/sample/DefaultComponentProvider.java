package com.star.plugin.sample;

import com.star.plugin.markdown.component.CharacterComponent;
import com.star.plugin.markdown.component.Component;
import com.star.plugin.markdown.component.HComponent;
import com.star.plugin.markdown.component.IndexComponent;
import com.star.plugin.markdown.component.LineComponent;
import com.star.plugin.markdown.component.QuoteComponent;
import com.star.plugin.markdown.component.StrikethroughComponent;
import com.star.plugin.markdown.component.provider.ComponentProvider;

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
//        components.add(new LinkComponent());
//        components.add(new MentionComponent());
        components.add(new StrikethroughComponent());
        components.add(new IndexComponent());
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }
}
