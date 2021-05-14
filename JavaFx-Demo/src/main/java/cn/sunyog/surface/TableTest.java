package cn.sunyog.surface;

import java.lang.reflect.Field;

import cn.sunyog.entity.EntityValueFactory;
import cn.sunyog.entity.Person;
import cn.sunyog.entity.TableItem;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * @Author: MysteriousGT
 * @Date: 2020/11/25 2:53 下午
 * @Desc: table
 */
public class TableTest {
    public static Pane getSimpleTable() {
        TableView table = new TableView();
        table.setEditable(true);
        TableColumn col1 = new TableColumn("姓名");
        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn col2 = new TableColumn("地址");
        col2.setCellValueFactory(new PropertyValueFactory<>("add"));
        TableColumn col3 = new TableColumn("邮箱");
        col3.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().addAll(col1, col2, col3);

        ObservableList<TableItem> list =
            FXCollections.observableArrayList(new TableItem("aaa", "aaaaaaa", "aaaaaaa@aa.cn", 12.0),
                new TableItem("bbb", "bbbbbbb", "bbbbbbb@bb.com", 13.0),
                new TableItem("ccc", "ccccccc", "ccccccc@cc.org", 14.4));
        table.setItems(list);
        table.autosize();
        Pane pane = new FlowPane();
        pane.getChildren().add(table);
        return pane;
    }

    public static Pane getEntityTable() {
        TableView table = new TableView();
        table.setEditable(true);
        TableColumn col1 = new TableColumn<>("姓名");
        col1.setCellValueFactory(new EntityValueFactory<Person>("name"));
        TableColumn col2 = new TableColumn<>("邮箱");
        col2.setCellValueFactory(new EntityValueFactory<Person>("email"));
        table.getColumns().addAll(col1, col2);
        ObservableList<Person> list = FXCollections.observableArrayList(new Person("aaa", "aaa", "aaaa@aa.com", 123.0),
            new Person("bbb", "bbb", "bbb@bb.com", 12.0));
        table.setItems(list);
        Pane pane = new AnchorPane();
        pane.getChildren().add(table);
        return pane;
    }

    public static Pane getTableEntity() {
        TableView table = new TableView();

        TableColumn col1 = new TableColumn<>("姓名");
        col1.setCellValueFactory(new Callback<CellDataFeatures<Person, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue call(CellDataFeatures<Person, Double> param) {
                return new SimpleDoubleProperty(param.getValue().getAge());
            }
        });
        TableColumn col2 = new TableColumn<>("邮箱");
        col2.setCellValueFactory(p -> {
            return new SimpleStringProperty(((CellDataFeatures<Person, Double>)p).getValue().getEmail());
        });
        table.getColumns().addAll(col1, col2);
        ObservableList<Person> list = FXCollections.observableArrayList(new Person("aaa", "aaa", "aaaa@aa.com", 123.0),
            new Person("bbb", "bbb", "bbb@bb.com", 12.0));

        table.setItems(list);

        Pane pane = new Pane();
        pane.getChildren().add(table);
        return pane;
    }

    public static Pane getTableEntityFactory() {
        TableView table = new TableView();
        table.getColumns().addAll(new TableColumn<>("name"), new TableColumn<>("add"), new TableColumn<>("email"),
            new TableColumn<>("age"));
        setTableValueFactury(table, Person.class);

        ObservableList<Person> items = FXCollections.observableArrayList(new Person("aaa", "aaa", "aaaa@aa.com", 123.0),
            new Person("bbb", "bbb", "bbb@bb.com", 12.0), new Person("ccc", "ccc", "ccc@ccc.xyz", 12.1));
        table.setItems(items);
        Pane pane = new Pane();
        pane.getChildren().add(table);
        return pane;
    }

    private static <T> void setTableValueFactury(TableView table, Class<T> t) {
        ObservableList<TableColumn> columns = table.getColumns();
        Field[] fields = t.getDeclaredFields();
        for (final Field field : fields) {
            field.setAccessible(true);
            ColumnIndex anno = field.getAnnotation(ColumnIndex.class);
            if (anno == null) {
                continue;
            }
            final Class<?> fieldClass = field.getType();
            int index = anno.value();
            TableColumn column = columns.get(index);
            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<T, ?>, ObservableValue<?>>() {
                @Override
                public ObservableValue call(TableColumn.CellDataFeatures<T, ?> param) {
                    T obj = param.getValue();
                    try {
                        Object value = field.get(obj);
                        // 返回对应的格式类型
                        if (value instanceof Boolean) {
                            return (ObservableValue<T>)new ReadOnlyBooleanWrapper((Boolean)value);
                        } else if (value instanceof Integer) {
                            return (ObservableValue<T>)new ReadOnlyIntegerWrapper((Integer)value);
                        } else if (value instanceof Float) {
                            return (ObservableValue<T>)new ReadOnlyFloatWrapper((Float)value);
                        } else if (value instanceof Long) {
                            return (ObservableValue<T>)new ReadOnlyLongWrapper((Long)value);
                        } else if (value instanceof Double) {
                            return (ObservableValue<T>)new ReadOnlyDoubleWrapper((Double)value);
                        } else if (value instanceof String) {
                            return (ObservableValue<T>)new ReadOnlyStringWrapper((String)value);
                        } else {
                            return new ReadOnlyObjectWrapper<T>((T)value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return new ReadOnlyObjectWrapper<Object>(null);
                    }
                }
            });
        }
    }
}
