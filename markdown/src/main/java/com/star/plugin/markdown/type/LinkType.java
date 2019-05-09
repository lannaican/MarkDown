package com.star.plugin.markdown.type;

import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.star.plugin.markdown.MarkDown;
import com.star.plugin.markdown.MarkDownHelper;
import com.star.plugin.markdown.listener.OnSpanClickListener;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.property.ImageLoader;
import com.star.plugin.markdown.span.LinkEditSpan;
import com.star.plugin.markdown.span.ImageSpan;
import com.star.plugin.markdown.span.UrlSpan;

import java.util.ArrayList;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 15:27
 */
public class LinkType implements MarkDownType {

    //一组图片集合
    private Spannable spannable;
    private ArrayList<String> images;

    @Override
    public String getRegex() {
        return "!{0,1}\\[.*?\\]\\(.*?\\)";
    }

    @Override
    public void setSpan(TextView textView, Spannable spannable, Item item, boolean edit) {
        boolean isImage = isImage(item.getText());
        if (isImage) {  //图片
            if (edit) {
                LinkEditSpan span = new LinkEditSpan(
                        MarkDown.getProperty().getImageDrawable(),
                        MarkDown.getProperty().getLinkDrawableSize(),
                        MarkDown.getProperty().getLinkEditGapWidth());
                MarkDownHelper.setSpan(spannable, span, item);
            } else {
                String des = getDes(item.getText());
                String url = getUrl(item.getText());
                ImageLoader loader = MarkDown.getProperty().getImageLoader();
                Bitmap bitmap = null;
                if (loader != null) {
                    bitmap = loader.getBitmap(url);
                }
                ImageSpan span = new ImageSpan(textView, bitmap, des) {
                    @Override
                    public void onSpanClick(View view) {
                        OnSpanClickListener clickListener = MarkDown.getProperty().getClickListener();
                        if (clickListener != null) {
                            clickListener.onImageClick(images, images.indexOf(url));
                        }
                    }
                };
                MarkDownHelper.setSpan(spannable, span, item);
                addImage(spannable, url);
            }
        } else {    //链接
            if (edit) {
                LinkEditSpan span = new LinkEditSpan(
                        MarkDown.getProperty().getUrlDrawable(),
                        MarkDown.getProperty().getLinkDrawableSize(),
                        MarkDown.getProperty().getLinkEditGapWidth());
                MarkDownHelper.setSpan(spannable, span, item);
            } else {
                String url = getUrl(item.getText());
                MarkDownHelper.setSpan(spannable, new UrlSpan() {
                    @Override
                    public void onSpanClick(View view) {
                        OnSpanClickListener clickListener = MarkDown.getProperty().getClickListener();
                        if (clickListener != null) {
                            clickListener.onUrlClick(url);
                        }
                    }
                }, item);
            }
        }
    }

    @Override
    public SpannableStringBuilder replaceString(SpannableStringBuilder builder, Item item) {
        String text = item.getText();
        if (isImage(text)) {
            int end = item.getEnd();
            if (end == builder.length() || (end < builder.length() && builder.charAt(end) != '\n')) {
                builder.insert(end, "\n");
            }
        } else {
            int start = item.getStart() + text.indexOf("[");
            int end = item.getStart() + text.lastIndexOf("]");
            builder.delete(end, item.getEnd()).delete(item.getStart(), start + 1);
        }
        if (item.getStart() > 0 && builder.charAt(item.getStart()-1) != '\n') {
            builder.insert(item.getStart(), "\n");
        }
        return builder;
    }

    /**
     * 获取描述
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
     * 获取网址
     */
    private String getUrl(String text) {
        int start = text.lastIndexOf("(");
        int end = text.lastIndexOf(")");
        if (end - start <= 1) {
            return "";
        }
        return text.substring(start + 1, end);
    }

    private boolean isImage(String text) {
        int index = text.indexOf("!");
        return index != -1 && index < text.indexOf("[") ;
    }

    private void addImage(Spannable spannable, String url) {
        if (this.spannable != spannable) {
            this.spannable = spannable;
            images = new ArrayList<>();
        }
        images.add(url);
    }
}
