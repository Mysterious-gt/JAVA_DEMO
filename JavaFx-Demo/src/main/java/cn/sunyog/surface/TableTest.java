package cn.sunyog.surface;

import cn.sunyog.entity.EntityValueFactory;
import cn.sunyog.entity.Person;
import cn.sunyog.entity.TableItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

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

        ObservableList<TableItem> list = FXCollections.observableArrayList(
                new TableItem("aaa", "aaaaaaa", "aaaaaaa@aa.cn", 12.0),
                new TableItem("bbb", "bbbbbbb", "bbbbbbb@bb.com", 13.0),
                new TableItem("ccc", "ccccccc", "ccccccc@cc.org", 14.4)
        );
        table.setItems(list);
        table.autosize();
        Pane pane = new FlowPane();
        pane.getChildren().add(table);
        return pane;
    }

    public static Pane getEntityTable() {
        TableView<Person> table = new TableView();
        table.setEditable(true);
        TableColumn col1 = new TableColumn<>("姓名");
        col1.setCellValueFactory(new EntityValueFactory<Person>("name"));
        TableColumn col2 = new TableColumn<>("邮箱");
        col2.setCellValueFactory(new EntityValueFactory<Person>("email"));
        table.getColumns().addAll(col1, col2);
        ObservableList<Person> list = FXCollections.observableArrayList(new Person("aaa", "aaa", "aaaa@aa.com",
                123.0), new Person("bbb", "bbb", "bbb@bb.com", 12.0));
        table.setItems(list);
        Pane pane = new AnchorPane();
        pane.getChildren().add(table);
        return pane;
    }

    public static void getTableEntity(){
        TableView table=new TableView();

    }
}
