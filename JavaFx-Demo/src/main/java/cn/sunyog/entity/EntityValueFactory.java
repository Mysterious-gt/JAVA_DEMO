package cn.sunyog.entity;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.lang.reflect.Field;

/**
 * @Author: MysteriousGT
 * @Date: 2020/11/27 11:01 上午
 * @Desc: person
 */
public class EntityValueFactory<T> implements Callback<CellDataFeatures<Object,T>, ObservableValue<T>> {
    private String fieldName;

    public EntityValueFactory(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public ObservableValue<T> call(CellDataFeatures<Object, T> param) {
        Object entity = param.getValue();
        try {
            Class<?> clazz = entity.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(entity);

            if (value instanceof Boolean) {
                return (ObservableValue<T>) new ReadOnlyBooleanWrapper((Boolean)value);
            } else if (value instanceof Integer) {
                return (ObservableValue<T>) new ReadOnlyIntegerWrapper((Integer)value);
            } else if (value instanceof Float) {
                return (ObservableValue<T>) new ReadOnlyFloatWrapper((Float)value);
            } else if (value instanceof Long) {
                return (ObservableValue<T>) new ReadOnlyLongWrapper((Long)value);
            } else if (value instanceof Double) {
                return (ObservableValue<T>) new ReadOnlyDoubleWrapper((Double)value);
            } else if (value instanceof String) {
                return (ObservableValue<T>) new ReadOnlyStringWrapper((String)value);
            }
            return new ReadOnlyObjectWrapper<T>((T)value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new ReadOnlyObjectWrapper<>(null);
    }
}
