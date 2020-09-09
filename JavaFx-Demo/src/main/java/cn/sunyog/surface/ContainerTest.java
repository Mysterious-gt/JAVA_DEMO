package cn.sunyog.surface;

import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @Author: jerrylee
 * @Date: 2020/8/31 11:15 上午
 * @Desc: 容器篇
 */
public class ContainerTest {

    public static TitledPane testTitledPane(){
        VBox box=new VBox();
        box.getChildren().addAll(new Text("计算机组成原理"),new Text("计算机网络"),new Text("操作系统"),new Text("数据结构"));
        TitledPane pane=new TitledPane("计算机基础知识",box);

        pane.setPadding(new Insets(10));
        return pane;
    }
}
