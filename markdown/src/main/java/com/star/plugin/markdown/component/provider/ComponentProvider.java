package com.star.plugin.markdown.component.provider;

import com.star.plugin.markdown.component.Component;

import java.util.List;

public interface ComponentProvider {

    /**
     * 根据场景获取转换组件
     */
    List<Component> getComponents(int scene);

}
