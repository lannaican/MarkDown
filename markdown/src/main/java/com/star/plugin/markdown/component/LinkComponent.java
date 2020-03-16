package com.star.plugin.markdown.component;

import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.listener.OnImageClickListener;
import com.star.plugin.markdown.listener.OnImageLoadListener;
import com.star.plugin.markdown.listener.OnUrlClickListener;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanType;
import com.star.plugin.markdown.span.ImageSpan;
import com.star.plugin.markdown.span.UrlSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 15:27
 */
public class LinkComponent implements Component {

    private int imageDesTextSize;
    private int imageDesTextColor;
    private int urlTextColor;
    private int urlPressBackgroundColor;

    private OnImageClickListener imageClickListener;
    private OnImageLoadListener imageLoadListener;
    private OnUrlClickListener urlClickListener;

    public LinkComponent(int imageDesTextSize,
                         int imageDesTextColor,
                         int urlTextColor,
                         int urlPressBackgroundColor,
                         @NonNull OnImageLoadListener imageLoadListener,
                         @NonNull OnImageClickListener imageClickListener,
                         @NonNull OnUrlClickListener urlClickListener) {
        this.imageDesTextSize = imageDesTextSize;
        this.imageDesTextColor = imageDesTextColor;
        this.urlTextColor = urlTextColor;
        this.urlPressBackgroundColor = urlPressBackgroundColor;
        this.imageLoadListener = imageLoadListener;
        this.imageClickListener = imageClickListener;
        this.urlClickListener = urlClickListener;
    }

    @Override
    public String getRegex() {
        return "!?\\[(((?!\\[).)*)?]\\(.*?\\)";
    }

    @Override
    public SpanInfo getSpanInfo(final TextView textView, String item, int start, int end, SpanType spanType) {
        boolean isImage = isImage(item);
        if (spanType == SpanType.Normal) {
            if (isImage) {
                String des = getDes(item);
                final String url = getUrl(item);
                Bitmap bitmap = imageLoadListener.getBitmap(textView.getContext(), url);
                ImageSpan span = new ImageSpan(textView, bitmap, des, imageDesTextSize, imageDesTextColor) {
                    @Override
                    public void onSpanClick(View view) {
                        ArrayList<String> images = new ArrayList<>();
                        List<Item> items = MarkDown.getInstance().getItems("!\\[(((?!\\[).)*)?]\\(.*?\\)", textView.getText());
                        for (Item i : items) {
                            images.add(getUrl(i.getText()));
                        }
                        imageClickListener.onClick(images, images.indexOf(url));
                    }
                };
                return new SpanInfo(span, start, end);
            } else {
                return getUrlSpanInfo(item, start, end);
            }
        } else {
            if (isImage) {
                return null;
            } else {
                return getUrlSpanInfo(item, start, end);
            }
        }
    }

    @Override
    public SpannableStringBuilder replaceText(SpannableStringBuilder builder, String item, int start, int end, SpanType spanType) {
        boolean isImage = isImage(item);
        if (isImage && spanType == SpanType.Simple) {
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
                if (start > 0 && builder.charAt(start-1) != '\n') {
                    builder.insert(start, "\n");
                }
            } else {
                int nStart = start + item.indexOf("[");
                int nEnd = start + item.lastIndexOf("]");
                builder.delete(nEnd, end).delete(start, nStart + 1);
            }
            return builder;
        }
    }


    private SpanInfo getUrlSpanInfo(String item, int start, int end) {
        final String url = getUrl(item);
        UrlSpan span = new UrlSpan(urlTextColor, urlPressBackgroundColor) {
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
