package cn.sunyog;

import cn.sunyog.surface.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: jerrylee
 * @Date: 2020/8/24 1:31 下午
 * @Desc: javafx启动类 继承 Application类
 */
public class FXApplication extends Application {
    private String msg = "DEFAULT";

    private Pane testBind() {
        DialogPane pane = new DialogPane();
        Text text = new Text(msg);

        return pane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Scene scene= SceneInit.initScene();

        StageInit.initStage(primaryStage);*/

//        Scene scene=new Scene(PaneTest.testFlowPane());
//        Scene scene=new Scene(PaneTest.testTilePane());
//        Scene scene=new Scene(PaneTest.testHBox());
//        Scene scene=new Scene(PaneTest.testVBox());
//        Scene scene=new Scene(PaneTest.testBorder());
//        Scene scene=new Scene(PaneTest.testAnchor());
//        Scene scene=new Scene(PaneTest.testGrid());
//        Scene scene=new Scene(PaneTest.testStack());
//        Scene scene=new Scene(PaneTest.testDialog());
//        Scene scene=new Scene(ContainerTest.testTitledPane());
//        Scene scene = new Scene(ImgTest.testImg());
//        Scene scene = new Scene(ProgressTest.testProgres());
//        Scene scene = new Scene(PaneTest.testScrollPane());
//      Scene scene=new Scene(WebTest.testWebUrl());
//        Scene scene = new Scene(WebTest.testHTMLJS());
//        Parent root = FXMLLoader.load(this.getClass().getResource("/view/fxml/test.fxml"));
//        Scene scene=new Scene(TableTest.getSimpleTable());
        Scene scene=new Scene(TableTest.getEntityTable());
//        Scene scene=new Scene(scene,400,600);
//        scene.getStylesheets().add("/view/css/test.css");
        primaryStage.setTitle("测试布局");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
