package cn.sunyog.surface;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;

/**
 * @Author: jerrylee
 * @Date: 2020/8/25 2:40 下午
 * @Desc: 布局
 */
public class PaneTest {
    private static void addChildren(Pane pane){
        Button bt1=new Button("button01");
        Button bt2=new Button("button02");
        Button bt3=new Button("button03");
        Text text=new Text("I am a Text.");
        Label l1=new Label("I am the first Label.");
        Label l2=new Label("I am the second Label.");

        pane.getChildren().addAll(bt1,l1,text,l2,bt2,bt3);
    }

    public static FlowPane testFlowPane(){
        FlowPane res=new FlowPane(Orientation.VERTICAL);
        //设置内部元素边距（距离flowPane边框的距离）
        res.setPadding(new Insets(20,20,20,20));
        //设置横向间隔
        res.setHgap(40);
        //设置纵向间隔
        res.setVgap(80);
        res.setPrefWrapLength(200);

        addChildren(res);
        return res;
    }

    public static TilePane testTilePane(){
        TilePane res=new TilePane(Orientation.HORIZONTAL);
        //设置初始列数，随界面变化
        res.setPrefColumns(4);
        res.setPadding(new Insets(20,20,20,20));
        res.setHgap(40);
        res.setVgap(40);
        //设置
        res.setTileAlignment(Pos.CENTER);
        addChildren(res);
        return res;
    }

    public static HBox testHBox(){
        HBox res=new HBox();
        res.setSpacing(10);
        res.setPadding(new Insets(20,20,20,20));

        addChildren(res);
        return res;
    }

    public static VBox testVBox(){
        //初始化设置间隔
        VBox res=new VBox(10);
        res.setPadding(new Insets(20,20,20,20));
        addChildren(res);
        return res;
    }

    public static BorderPane testBorder(){
        BorderPane res=new BorderPane();
        res.setPrefSize(600,400);
        res.setPadding(new Insets(20,20,20,20));

        Button topBtn = new Button("top button");
        BorderPane.setMargin(topBtn,new Insets(10,10,10,10));
        BorderPane.setAlignment(topBtn,Pos.CENTER_RIGHT);
        res.setTop(topBtn);
        res.setBottom(new Button("bottom button"));
        res.setLeft(new Button("left button"));
        res.setRight(new Button("right button"));
        res.setCenter(new Button("center button"));

        return res;
    }

    public static AnchorPane testAnchor(){
        AnchorPane res=new AnchorPane();
        res.setPrefSize(600,400);
        res.setPadding(new Insets(10,10,10,10));

        Button btn1=new Button("btn anchor top bottom left right");
        //设定到锚定点距离
        AnchorPane.setTopAnchor(btn1,10.0);
        AnchorPane.setLeftAnchor(btn1,15.0);
        AnchorPane.setRightAnchor(btn1,20.0);
        AnchorPane.setBottomAnchor(btn1,25.0);
        Button btn2=new Button("btn bottom");
        AnchorPane.setBottomAnchor(btn2,5.0);

        res.getChildren().addAll(btn1,btn2);
        return res;
    }

    public static GridPane testGrid(){
        GridPane res=new GridPane();
        res.setPrefSize(400,300);
        res.setHgap(20);
        res.setVgap(20);
        res.setPadding(new Insets(10,19,10,10));

        Text text=new Text("I am a text, i span 3 col and 1 row.");
        //设置元素起始列，起始行，所占列数，行数
        res.add(text,0,0,3,2);
        //设置显示样式
        GridPane.setHalignment(text ,HPos.RIGHT);
        Button bt1=new Button("button-1");
        //设置元素所在列，所在行
        res.add(bt1,0,2);
        Button bt2=new Button("button-2");
        res.add(bt2,1,2);
        Button bt3=new Button("button-3");
        res.add(bt3,2,2);
        return res;
    }

    public static VBox testStack(){
        StackPane pane=new StackPane();

        Button bt1=new Button("first");
        bt1.setStyle("-fx-font: bold italic 50px \"sans-serif\"");
        pane.getChildren().add(bt1);
        Text text=new Text("second");
        text.setStyle("-fx-font: bold italic 50px \"sans-serif\"");
        pane.getChildren().add(text);
        Button bt2=new Button("third");
        bt2.setStyle("-fx-font: bold italic 50px \"sans-serif\"");
        pane.getChildren().add(bt2);

        pane.setPrefSize(300,200);
        pane.setPadding(new Insets(20,20,20,20));
        pane.setAlignment(Pos.CENTER);

        VBox res=new VBox();
        res.getChildren().addAll(pane,stackControl(pane));
        return res;
    }

    private static Button stackControl(StackPane pane){
        Button bt=new Button("栈顶到栈底");

        bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ObservableList<Node> children = pane.getChildren();
                if (children.size()>1){
                    Node node = children.get(children.size() - 1);
                    node.toBack();
                }
            }
        });

        return bt;
    }

    public static DialogPane testDialog(){
        DialogPane pane=new DialogPane();
        pane.setHeaderText("I am Headers");
        pane.setContentText("I am a content text.");
        Text text = new Text("Graphic");
        text.setStyle("-fx-font: bold italic 50px 'sans-serif'");
        pane.setGraphic(text);

        pane.getButtonTypes().addAll(ButtonType.APPLY,ButtonType.OK,ButtonType.CLOSE);
        pane.setPrefSize(300,200);
        pane.setPadding(new Insets(20,20,20,20));
        return pane;
    }
}
