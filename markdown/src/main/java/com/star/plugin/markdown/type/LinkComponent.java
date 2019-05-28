package com.star.plugin.markdown.type;

import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.span.ImageSpan;
import com.star.plugin.markdown.span.LinkEditSpan;
import com.star.plugin.markdown.span.UrlSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 15:27
 */
public abstract class LinkComponent implements Component {

    @Override
    public String getRegex() {
        return "!{0,1}\\[.*?\\]\\(.*?\\)";
    }

    @Override
    public SpanInfo getSpanInfo(final TextView textView, final String item, int start, int end, SpanStyle style) {
        boolean isImage = isImage(item);
        if (style == SpanStyle.Simple) {
            if (isImage) {
                return null;
            } else {
                return getUrlDisplaySpanInfo(item, start, end);
            }
        } else if (style == SpanStyle.Editing) {
            if (isImage) {
                LinkEditSpan span = new LinkEditSpan(
                        MarkDown.getProperty().getImageDrawable(),
                        MarkDown.getProperty().getLinkDrawableSize(),
                        MarkDown.getProperty().getLinkEditGapWidth());
                return new SpanInfo(span, start, end);
            } else {
                LinkEditSpan span = new LinkEditSpan(
                        MarkDown.getProperty().getUrlDrawable(),
                        MarkDown.getProperty().getLinkDrawableSize(),
                        MarkDown.getProperty().getLinkEditGapWidth());
                return new SpanInfo(span, start, end);
            }
        } else if (style == SpanStyle.Display) {
            if (isImage) {
                String des = getDes(item);
                final String url = getUrl(item);
                Bitmap bitmap = getBitmap(url);
                ImageSpan span = new ImageSpan(textView, bitmap, des) {
                    @Override
                    public void onSpanClick(View view) {
                        ArrayList<String> images = new ArrayList<>();
                        List<Item> items = MarkDown.getItems("!\\[.*?\\]\\(.*?\\)", textView.getText());
                        for (Item i : items) {
                            images.add(getUrl(i.getText()));
                        }
                        onImageClick(images, images.indexOf(url));
                    }
                };
                return new SpanInfo(span, start, end);
            } else {
                return getUrlDisplaySpanInfo(item, start, end);
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

    /**
     * 获取Bitmap
     */
    public abstract Bitmap getBitmap(String url);

    /**
     * 点击图片
     */
    public abstract void onImageClick(ArrayList<String> images, int index);

    /**
     * 点击链接
     */
    public abstract void onUrlClick(String url);


    private SpanInfo getUrlDisplaySpanInfo(String item, int start, int end) {
        final String url = getUrl(item);
        UrlSpan span = new UrlSpan() {
            @Override
            public void onSpanClick(View view) {
                onUrlClick(url);
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
