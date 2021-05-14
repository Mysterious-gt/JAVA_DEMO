package cn.sunyog.surface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: MysteriousGT
 * @Date: 2020/12/9 1:43 下午
 * @Desc: 输入框
 */
public class FieldTest {
    public static Pane searchField() {
        String[] db = new String[] {"aaaaaaaaa", "bbbbbbbbb", "aabb", "aaaabbbb", "abababab", "ccccccc"};
        ObservableList<String> strings = FXCollections.observableArrayList(db);
        Pane pane = new Pane();
        ComboBox<String> box=new ComboBox();
        box.setItems(strings);
        box.setEditable(true);

        box.valueProperty().addListener((a,b,c)->{
            System.out.println(c);
            List<String> collect =
                    Arrays.asList(db).stream().filter(item -> item.contains(c)).collect(Collectors.toList());
            box.setItems(FXCollections.observableArrayList(collect));
        });
        pane.getChildren().addAll(box);
        return pane;
    }
}
