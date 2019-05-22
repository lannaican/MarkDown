package com.star.plugin.markdown.type.provider;

import com.star.plugin.markdown.type.CharacterType;
import com.star.plugin.markdown.type.HType;
import com.star.plugin.markdown.type.LinkType;
import com.star.plugin.markdown.type.IndexType;
import com.star.plugin.markdown.type.LineType;
import com.star.plugin.markdown.type.MarkDownType;
import com.star.plugin.markdown.type.MentionType;
import com.star.plugin.markdown.type.QuoteType;
import com.star.plugin.markdown.type.StrikethroughType;

import java.util.ArrayList;
import java.util.List;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 9:28
 */
public class DefaultMarkDownTypeProvider implements MarkDownTypeProvider {

    private List<MarkDownType> types;

    public DefaultMarkDownTypeProvider() {
        types = new ArrayList<>();
        types.add(new HType());
        types.add(new CharacterType());
        types.add(new QuoteType());
        types.add(new LineType());
        types.add(new LinkType());
        types.add(new MentionType());
        types.add(new StrikethroughType());
        types.add(new IndexType());
    }

    @Override
    public List<MarkDownType> getTypes() {
        return types;
    }
}
