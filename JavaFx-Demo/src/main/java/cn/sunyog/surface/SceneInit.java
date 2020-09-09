package cn.sunyog.surface;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @Author: jerrylee
 * @Date: 2020/8/24 2:09 下午
 * @Desc: 界面之 scene
 */
public class SceneInit {
    public static Scene initScene(){
        Text text=new Text("我是场景");
        text.getStyleClass().add("text-class");

        Scene scene=new Scene(new TextFlow(text));
        scene.getStylesheets().add("/view/css/test.css");
        scene.setFill(Color.GREEN);
        return scene;
    }
}
