package com.star.plugin.markdown.component;

import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.listener.OnImageClickListener;
import com.star.plugin.markdown.listener.OnImageLoadListener;
import com.star.plugin.markdown.listener.OnUrlClickListener;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.property.MarkDownProperty;
import com.star.plugin.markdown.span.ImageSpan;
import com.star.plugin.markdown.span.LinkEditSpan;
import com.star.plugin.markdown.span.UrlSpan;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 15:27
 */
public class LinkComponent implements Component {

    private OnImageClickListener imageClickListener;
    private OnImageLoadListener imageLoadListener;
    private OnUrlClickListener urlClickListener;

    public LinkComponent(@NonNull OnImageLoadListener imageLoadListener,
                         @NonNull OnImageClickListener imageClickListener,
                         @NonNull OnUrlClickListener urlClickListener) {
        this.imageLoadListener = imageLoadListener;
        this.imageClickListener = imageClickListener;
        this.urlClickListener = urlClickListener;
    }

    @Override
    public String getRegex() {
        return "!{0,1}\\[.*?\\]\\(.*?\\)";
    }

    @Override
    public SpanInfo getSpanInfo(final TextView textView, final String item, int start, int end, SpanStyle style) {
        MarkDownProperty property = MarkDown.getInstance().getProperty();
        boolean isImage = isImage(item);
        if (style == SpanStyle.Simple) {
            if (isImage) {
                return null;
            } else {
                return getUrlDisplaySpanInfo(property, item, start, end);
            }
        } else if (style == SpanStyle.Editing) {
            if (isImage) {
                LinkEditSpan span = new LinkEditSpan(
                        property.getImageDrawable(),
                        property.getLinkDrawableSize(),
                        property.getLinkEditGapWidth());
                return new SpanInfo(span, start, end);
            } else {
                LinkEditSpan span = new LinkEditSpan(
                        property.getUrlDrawable(),
                        property.getLinkDrawableSize(),
                        property.getLinkEditGapWidth());
                return new SpanInfo(span, start, end);
            }
        } else if (style == SpanStyle.Display) {
            if (isImage) {
                String des = getDes(item);
                final String url = getUrl(item);
                Bitmap bitmap = imageLoadListener.getBitmap(textView.getContext(), url);
                ImageSpan span = new ImageSpan(textView, bitmap, des) {
                    @Override
                    public void onSpanClick(View view) {
                        ArrayList<String> images = new ArrayList<>();
                        List<Item> items = MarkDown.getInstance().getItems("!\\[.*?\\]\\(.*?\\)", textView.getText());
                        for (Item i : items) {
                            images.add(getUrl(i.getText()));
                        }
                        imageClickListener.onClick(images, images.indexOf(url));
                    }
                };
                return new SpanInfo(span, start, end);
            } else {
                return getUrlDisplaySpanInfo(property, item, start, end);
            }
        }
        return null;
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, ReplaceStyle style) {
        boolean isImage = isImage(item);
        if (isImage && style == ReplaceStyle.Origin) {
            if (end < builder.length() && builder.charAt(end) == '\n') {
                end++;
            }
            builder.delete(start, end);
            return builder;
        } else {
            if (isImage) {
                if (end == builder.length() || (end < builder.length() && builder.charAt(end) != '\n')) {
                    builder.insert(end, "\n");
                }
            } else {
                int nStart = start + item.indexOf("[");
                int nEnd = start + item.lastIndexOf("]");
                builder.delete(nEnd, end).delete(start, nStart + 1);
            }
            if (start > 0 && builder.charAt(start-1) != '\n') {
                builder.insert(start, "\n");
            }
            return builder;
        }
    }


    private SpanInfo getUrlDisplaySpanInfo(MarkDownProperty property, String item, int start, int end) {
        final String url = getUrl(item);
        UrlSpan span = new UrlSpan(property.getColor(), property.getPressBackgroundColor()) {
            @Override
            public void onSpanClick(View view) {
                urlClickListener.onClick(url);
            }
        };
        return new SpanInfo(span, start, end);
    }

    /**
     * 获取图片描述
     */
    private String getDes(String text) {
        int start = text.indexOf("[");
        int end = text.lastIndexOf("]");
        if (end - start <= 1) {
            return "";
        }
        return text.substring(start + 1, end);
    }

    /**
     * 获取网址或图片地址
     */
    private String getUrl(String text) {
        int start = text.lastIndexOf("(");
        int end = text.lastIndexOf(")");
        if (end - start <= 1) {
            return "";
        }
        return text.substring(start + 1, end);
    }

    /**
     * 判断是图片还是链接
     */
    private boolean isImage(String text) {
        int index = text.indexOf("!");
        return index != -1 && index < text.indexOf("[") ;
    }
}