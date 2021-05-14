package cn.sunyog.surface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: MysteriousGT
 * @Date: 2020/11/27 11:30 上午
 * @Desc: 表格 > 单元格下标
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnIndex {
    int value() default 0;
}
