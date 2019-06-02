package com.star.plugin.markdown;

import android.annotation.SuppressLint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import com.star.plugin.markdown.listener.OnMarkDownListener;
import com.star.plugin.markdown.model.Item;
import com.star.plugin.markdown.model.ReplaceStyle;
import com.star.plugin.markdown.model.SpanInfo;
import com.star.plugin.markdown.model.SpanStyle;
import com.star.plugin.markdown.property.MarkDownProperty;
import com.star.plugin.markdown.component.Component;
import com.star.plugin.markdown.component.provider.ComponentProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    private MarkDownProperty property;

    public static void init(ComponentProvider provider, MarkDownProperty property) {
        markDown = new MarkDown(provider, property);
    }

    public static MarkDown getInstance() {
        return markDown;
    }

    private MarkDown(ComponentProvider provider, MarkDownProperty property) {
        this.property = property;
        this.provider = provider;
    }

    public MarkDownProperty getProperty() {
        return property;
    }

    /**
     * 异步加载
     * 显示图片必须使用异步
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void loadAsync(final TextView textView, final String text,
                          final SpanStyle spanStyle, final ReplaceStyle replaceStyle,
                          final OnMarkDownListener listener, final Class...components) {
        if (text.length() == 0) {
            textView.setText(null);
            if (listener != null) {
                listener.onFinish(text);
            }
            return;
        }
        Observable.fromCallable(new Callable<SpannableStringBuilder>() {
            @Override
            public SpannableStringBuilder call() {
                return getSpan(textView, text, spanStyle, replaceStyle, components);
            }})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SpannableStringBuilder>() {
                    @Override
                    public void accept(SpannableStringBuilder builder) {
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
    public void load(TextView textView, String text, SpanStyle style, ReplaceStyle replaceStyle, Class...useComponents) {
        if (text == null || text.length() == 0) {
            textView.setText(null);
            return;
        }
        textView.setText(getSpan(textView, text, style, replaceStyle, useComponents));
    }

    /**
     * 编辑模式设置样式
     */
    public void set(TextView textView, Spannable spannable, Class...useComponents) {
        MarkDownHelper.clearSpan(spannable);
        List<Component> components = provider.getComponents();
        for (Component component : components) {
            if (isInvalidComponent(component, useComponents)) {
                continue;
            }
            List<Item> items = getItems(component.getRegex(), spannable);
            for (Item item : items) {
                SpanInfo info = component.getSpanInfo(textView, item.getText(),
                        item.getStart(), item.getEnd(), SpanStyle.Editing);
                MarkDownHelper.setSpan(spannable, info);
            }
        }
    }

    /**
     * 获取展示Span
     */
    public SpannableStringBuilder getSpan(TextView textView, String text, SpanStyle spanStyle,
                                                 ReplaceStyle replaceStyle, Class...useComponents) {
        List<Component> components = provider.getComponents();
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        for (Component component : components) {
            if (isInvalidComponent(component, useComponents)) {
                continue;
            }
            List<Item> items = getItems(component.getRegex(), text);
            for (Item item : items) {
                SpanInfo info = component.getSpanInfo(textView, item.getText(),
                        item.getStart(), item.getEnd(), spanStyle);
                MarkDownHelper.setSpan(builder, info);
            }
        }
        int diffCount;
        for (Component component : components) {
            if (isInvalidComponent(component, useComponents)) {
                continue;
            }
            List<Item> items = getItems(component.getRegex(), builder);
            diffCount = text.length() - builder.length();
            int diffLength = 0;
            for (Item item : items) {
                item.setStart(item.getStart() - diffLength);
                item.setEnd(item.getEnd() - diffLength);
                SpannableStringBuilder newBuilder;
                newBuilder = component.replaceText(builder, item.getText(),
                        item.getStart(), item.getEnd(), replaceStyle);
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
            strings.add(item.getText());
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
            if (item.getText().startsWith("\n")) {  //针对首字符换行处理
                item.setText(item.getText().substring(1));
                item.setStart(item.getStart() + 1);
            }
            items.add(item);
        }
        return items;
    }

    /**
     * 判断类型无效
     */
    private boolean isInvalidComponent(Component component, Class...useComponents) {
        if (useComponents == null || useComponents.length == 0) {
            return false;
        }
        for (Class cls : useComponents) {
            if (component.getClass() == cls) {
                return false;
            }
        }
        return true;
    }
}
