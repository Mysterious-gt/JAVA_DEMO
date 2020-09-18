package cn.sunyog.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jerrylee
 * @Date: 2020/8/24 2:43 下午
 * @Desc: text
 */
public class TextController {
    @FXML
    public VBox root;
    @FXML
    public Label label;
    @FXML
    public Label label0;
    @FXML
    public Label label1;
    @FXML
    public Label label2;
    @FXML
    public Label label3;
    @FXML
    public Label label4;
    @FXML
    public Button button;
    @FXML
    public RadioButton rb1;
    @FXML
    public RadioButton rb2;
    @FXML
    public RadioButton rb3;
    @FXML
    public ToggleButton tb1;
    @FXML
    public ToggleButton tb2;
    @FXML
    public ToggleButton tb3;

    @FXML
    public void initialize() throws IOException {
        this.testLabel();
        this.testButton();
        this.testRadioButton();
        this.testToggleButton();
    }

    private void testToggleButton() {
        ToggleGroup group=new ToggleGroup();
        tb1.setToggleGroup(group);
        tb1.setUserData(1.0);
        tb2.setToggleGroup(group);
        tb2.setUserData(0.5);
        tb3.setToggleGroup(group);
        tb3.setUserData(1.5);

        HBox hbox=new HBox();
        hbox.getChildren().addAll(tb1,tb2,tb3);
        root.getChildren().add(hbox);

        //选中事件
        group.selectedToggleProperty().addListener((list,t1,t2)->{
            if (group.getSelectedToggle()!=null){
                Object scale = group.getSelectedToggle().getUserData();
                double scalInt = (double) scale;
                this.label2.setScaleX(scalInt);
                this.label2.setScaleY(scalInt);
            }
        });
        tb1.setSelected(true);
        tb1.requestFocus();
    }

    //单选按钮
    private void testRadioButton() {
        ToggleGroup group=new ToggleGroup();
        rb1.setToggleGroup(group);
        rb1.setUserData("red");
        rb2.setToggleGroup(group);
        rb2.setUserData("green");
        rb3.setToggleGroup(group);
        rb3.setUserData("blue");

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (group.getSelectedToggle()!=null){
                    Object color = group.getSelectedToggle().getUserData();
                    label2.setStyle("-fx-background-color: "+color);
                }
            }
        });
        //初始化选中
        rb3.setSelected(true);
        //设置请求焦点
        rb2.requestFocus();
    }

    private void testButton() throws IOException {
        Button btn = this.button;
        //按钮点击事件
        btn.setOnAction(e -> {
            Label label = this.label2;
            double rotate = label.getRotate();
            label.setRotate(rotate+30.0);
        });
        //鼠标拖入
        btn.setOnMouseDragEntered(e -> this.label.setText("MouseDragEvent"));
        //鼠标拖出
        btn.setOnMouseDragExited(e -> this.label.setText("MouseDragExited"));
        //鼠标移动
        //btn.setOnMouseMoved(e -> this.label.setText("MouseMoved"));
        //鼠标点击
        //btn.setOnMouseClicked(e -> this.label.setText("MouseClicked"));
        //鼠标移入
        btn.setOnMouseEntered(e -> this.label.setText("MouseEntered"));
        //鼠标移出
        btn.setOnMouseExited(e -> this.label.setText("MouseExited"));
        //鼠标按压
        btn.setOnMousePressed(e -> this.label.setText("MousePressed"));
        //鼠标释放
        btn.setOnMouseReleased(t -> this.label.setText("MouseRelease"));

        //鼠标进入增加阴影效果
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,e->btn.setEffect(new DropShadow()));
        //鼠标移出取消效果
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,e->btn.setEffect(null));
    }

    private void testLabel() throws IOException {
        this.label1.setWrapText(true);
        this.label2.getStyleClass().add("label-class");
        this.label3.setOnMouseClicked(e->{
            double rotate = this.label3.getRotate();
            this.label3.setRotate(rotate+90);
        });
        Label label = this.label4;
        ImageView view = getImageView();
        label.setGraphic(view);
    }

    private ImageView getImageView() throws IOException {
        ImageView view = new ImageView();
        try (InputStream in = this.getClass().getResourceAsStream("/view/img/zcfg.png")) {
            Image img = new Image(in);
            view.setImage(img);
        }
        return view;
    }


}
