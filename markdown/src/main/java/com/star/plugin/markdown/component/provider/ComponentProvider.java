package com.star.plugin.markdown.component.provider;

import com.star.plugin.markdown.component.Component;

import java.util.List;

/**
 * Detail：
 * Author：Stars
 * Create Time：2019/5/7 9:28
 */
public interface ComponentProvider {

    /**
     * 代码块必须放最前面
     */
    List<Component> getComponents();

}
