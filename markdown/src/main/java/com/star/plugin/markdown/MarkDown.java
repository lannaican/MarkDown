package com.star.plugin.markdown;

import android.annotation.SuppressLint;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.TextView;

import com.star.plugin.markdown.component.Component;
import com.star.plugin.markdown.component.provider.ComponentProvider;
import com.star.plugin.markdown.listener.OnMarkDownListener;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.model.SpanInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 8:38
 */
public class MarkDown {

    private static MarkDown markDown;

    private ComponentProvider provider;

    public static void init(ComponentProvider provider) {
        markDown = new MarkDown(provider);
    }

    public static MarkDown getInstance() {
        return markDown;
    }

    private MarkDown(ComponentProvider provider) {
        this.provider = provider;
    }

    /**
     * 异步加载
     * 显示图片必须使用异步
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void loadAsync(final TextView textView, final String text, final int scene, final OnMarkDownListener listener) {
        if (text==null || text.length() == 0) {
            textView.setText(null);
            if (listener != null) {
                listener.onFinish(text);
            }
            return;
        }
        Observable.fromCallable(new Callable<SpannableStringBuilder>() {
            @Override
            public SpannableStringBuilder call() {
                return getSpan(textView, text, scene);
            }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SpannableStringBuilder>() {
                    @Override
                    public void accept(final SpannableStringBuilder builder) {
                        textView.setText(builder, TextView.BufferType.SPANNABLE);
                        if (listener != null) {
                            listener.onFinish(text);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 同步加载
     */
    public void load(TextView textView, String text, int scene) {
        if (text == null || text.length() == 0) {
            textView.setText(null);
            return;
        }
        textView.setText(getSpan(textView, text, scene));
    }

    /**
     * 获取展示Span
     */
    public SpannableStringBuilder getSpan(TextView textView, String text, int scene) {
        if (TextUtils.isEmpty(text)) {
            return new SpannableStringBuilder();
        }
        List<Component> components = provider.getComponents(scene);
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        Map<Component, List<Item>> cache = new HashMap<>();
        for (Component component : components) {
            List<Item> items = getItems(component.getRegex(), text);
            if (items == null || items.isEmpty()) {
                continue;
            }
            cache.put(component, items);
            for (Item item : items) {
                SpanInfo info = component.getSpanInfo(textView, item.text, item.start, item.end);
                MarkDownHelper.setSpan(builder, info);
            }
        }
        int diffCount;
        for (Component component : components) {
            List<Item> items = cache.get(component);
            if (items == null) {
                continue;
            }
            diffCount = text.length() - builder.length();
            int diffLength = 0;
            for (Item item : items) {
                item.start = item.start - diffLength;
                item.end = item.end - diffLength;
                SpannableStringBuilder newBuilder;
                newBuilder = component.replaceText(builder, item.text, item.start, item.end);
                diffLength = text.length() - diffCount - newBuilder.length();
                builder = newBuilder;
            }
        }
        return builder;
    }

    /**
     * 获取满足正则的字符串集合
     */
    public ArrayList<String> findStringList(String text, String regex) {
        ArrayList<String> strings = new ArrayList<>();
        List<Item> items = getItems(regex, text);
        for (Item item : items) {
            strings.add(item.text);
        }
        return strings;
    }

    /**
     * 获取所有满足正则项
     */
    public List<Item> getItems(String regex, CharSequence text) {
        List<Item> items = new LinkedList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            Item item = new Item(matcher.start(), matcher.end(), matcher.group());
            if (item.text.startsWith("\n")) {  //针对首字符换行处理
                item.text = item.text.substring(1);
                item.start = item.start + 1;
            }
            items.add(item);
        }
        return items;
    }

}
