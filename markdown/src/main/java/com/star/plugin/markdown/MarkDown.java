package com.star.plugin.markdown;

import android.annotation.SuppressLint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.listener.OnMarkDownListener;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.property.MarkDownProperty;
import com.star.plugin.markdown.type.MarkDownType;
import com.star.plugin.markdown.type.provider.MarkDownTypeProvider;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 8:38
 */
public class MarkDown {

    private static MarkDownTypeProvider provider;
    private static MarkDownProperty property;

    public static void init(MarkDownTypeProvider provider, MarkDownProperty property) {
        MarkDown.provider = provider;
        MarkDown.property = property;
    }

    public static MarkDownProperty getProperty() {
        return property;
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public static void set(TextView textView, String text, OnMarkDownListener listener) {
        Observable.fromCallable(() -> getDisplaySpan(text)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(builder -> {
                    textView.setText(builder, TextView.BufferType.SPANNABLE);
                    listener.onFinish(text);
                });
    }

    /**
     * 编辑模式下设置Span
     */
    public static void setEditing(Spannable spannable) {
        MarkDownHelper.clearSpan(spannable);
        List<MarkDownType> types = provider.getTypes();
        for (MarkDownType type : types) {
            List<Item> items = matchType(type, spannable);
            for (Item item : items) {
                type.setSpan(spannable, item, true);
            }
        }
    }

    /**
     * 获取展示Span,非UI线程
     */
    public static SpannableStringBuilder getDisplaySpan(String text) {
        List<MarkDownType> types = provider.getTypes();
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        for (MarkDownType type : types) {
            List<Item> items = matchType(type, text);
            for (Item item : items) {
                type.setSpan(builder, item, false);
            }
        }
        int diffCount;
        for (MarkDownType type : types) {
            List<Item> items = matchType(type, builder);
            diffCount = text.length() - builder.length();
            int diffLength = 0;
            for (Item item : items) {
                item.setStart(item.getStart() - diffLength);
                item.setEnd(item.getEnd() - diffLength);
                SpannableStringBuilder newBuilder;
                newBuilder = type.replaceString(builder, item);
                diffLength = text.length() - diffCount - newBuilder.length();
                builder = newBuilder;
            }
        }
        return builder;
    }

    private static List<Item> matchType(MarkDownType type, CharSequence text) {
        List<Item> items = new LinkedList<>();
        Pattern pattern = Pattern.compile(type.getRegex());
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            items.add(new Item(type, matcher.start(), matcher.end(), matcher.group()));
        }
        return items;
    }
}
