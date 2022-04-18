package com.secret.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 常量帮助类
 *
 * @author laodeng
 * @Email befamouscoder@163.com
 */
@Component
@PropertySource("classpath:config.properties")
public class Constant {

    /**
     * 菜单类型
     *
     * @author laodeng
     * @Email befamouscoder@163.com
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }



}
