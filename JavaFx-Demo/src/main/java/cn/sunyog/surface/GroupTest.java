package cn.sunyog.surface;

import javafx.scene.Group;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @Author: MysteriousGT
 * @Date: 2021/1/4 11:28 上午
 * @Desc: group test
 */
public class GroupTest {
    public static Pane selectPaneTest(){
        RadioButton r1=new RadioButton("计算");
        RadioButton r2 = new RadioButton("不计算");
        ToggleGroup g = new ToggleGroup();
        r1.setToggleGroup(g);
        r2.setToggleGroup(g);
        r1.setSelected(true);
        VBox box = new VBox();
        box.getChildren().addAll(r1,r2);
        Pane pane=new AnchorPane(box);

        return pane;
    }
}
