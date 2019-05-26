package com.star.plugin.markdown.property;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.star.plugin.markdown.R;
import com.star.plugin.markdown.listener.OnSpanClickListener;

/**
 * Detail：属性
 * Author：Stars
 * Create Time：2019/5/7 8:38
 */
public class DefaultMarkDownProperty implements MarkDownProperty {

    private Context context;

    public DefaultMarkDownProperty(Context context) {
        this.context = context;
    }

    @Override
    public float getLineSpacingMultiplier() {
        return 1.5f;
    }

    @Override
    public int getColor() {
        return 0xFFFB7299;
    }

    @Override
    public int getPressBackgroundColor() {
        return 0xFFE3E3E3;
    }

    @Override
    public int getHColor() {
        return 0xFF3B3B3B;
    }

    @Override
    public int getHSize(int level) {
        switch (level) {
            case 1: return getSize(R.dimen.md_h1_size);
            case 2: return getSize(R.dimen.md_h2_size);
            case 3: return getSize(R.dimen.md_h3_size);
            case 4: return getSize(R.dimen.md_h4_size);
            case 5: return getSize(R.dimen.md_h5_size);
            case 6: return getSize(R.dimen.md_h6_size);
        }
        return 0;
    }

    @Override
    public int getQuoteWidth() {
        return context.getResources().getDimensionPixelSize(R.dimen.md_quote_width);
    }

    @Override
    public int getQuoteGapWidth() {
        return context.getResources().getDimensionPixelSize(R.dimen.md_quote_gap_width);
    }

    @Override
    public int getQuoteColor() {
        return 0xFFE3E3E3;
    }

    @Override
    public float getLineHeight() {
        return getSize(R.dimen.md_line_height);
    }

    @Override
    public int getLineColor() {
        return 0xFFE3E3E3;
    }

    @Override
    public Drawable getImageDrawable() {
        return context.getResources().getDrawable(R.drawable.md_image);
    }

    @Override
    public Drawable getUrlDrawable() {
        return context.getResources().getDrawable(R.drawable.md_url);
    }

    @Override
    public int getLinkDrawableSize() {
        return getSize(R.dimen.md_link_drawable_size);
    }

    @Override
    public int getLinkEditGapWidth() {
        return getSize(R.dimen.md_link_edit_gap_width);
    }

    @Override
    public ImageLoader getImageLoader() {
        return null;
    }

    @Override
    public int getImageDesSize() {
        return getSize(R.dimen.md_image_font_size);
    }

    @Override
    public int getImageDesColor() {
        return 0xFFAAAAAA;
    }

    @Override
    public OnSpanClickListener getClickListener() {
        return null;
    }

    @Override
    public int getIndexPadding() {
        return getSize(R.dimen.md_index_padding);
    }

    private int getSize(int id) {
        return context.getResources().getDimensionPixelSize(id);
    }

}
