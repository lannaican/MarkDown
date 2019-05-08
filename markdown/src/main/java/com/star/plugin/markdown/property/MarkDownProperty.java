package com.star.plugin.markdown.property;

import android.graphics.drawable.Drawable;

import com.star.plugin.markdown.listener.OnSpanClickListener;

/**
 * Detail：属性
 * Author：Stars
 * Create Time：2019/5/7 8:38
 */
public interface MarkDownProperty {

    //行距倍数
    float getLineSpacingMultiplier();

    //强调字体色
    int getColor();

    //按下背景色
    int getPressBackgroundColor();

    //标题字体色
    int getHColor();

    //标题大小 1-6
    int getHSize(int level);

    //引用宽度
    int getQuoteWidth();

    //引用间距
    int getQuoteGapWidth();

    //引用颜色
    int getQuoteColor();

    //分割线高度
    float getLineHeight();

    //分割线颜色
    int getLineColor();

    //编辑模式图片
    Drawable getImageDrawable();

    //编辑模式链接
    Drawable getUrlDrawable();

    //编辑模式图片大小
    int getLinkDrawableSize();

    //编辑模式图片间距
    int getLinkEditGapWidth();

    //浏览模式图片加载器
    ImageLoader getImageLoader();

    //浏览模式图片高度
    int getImageHeight();

    int getImageDesSize();

    int getImageDesColor();

    //点击监听
    OnSpanClickListener getClickListener();

    int getIndexPadding();

}
