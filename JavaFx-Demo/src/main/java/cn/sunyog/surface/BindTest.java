package cn.sunyog.surface;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * @Author: MysteriousGT
 * @Date: 2020/12/1 9:23 上午
 * @Desc: 绑定测试
 */
public class BindTest {
    /**
     * @Desc: 单属性监听
     * @Author: MysteriousGT
     * @Date: 2020/12/1
     * @Param: []
     * @Return: javafx.scene.layout.Pane
     */
    public static Pane propertyListener(){
        TextField field01=new TextField();
        TextField field02=new TextField();

        field02.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                field01.setText(newValue);
            }
        });
        return new VBox(field01,field02);
    }

    public static Pane binPropertyBind(){
        TextField field01=new TextField();
        TextField field02=new TextField();

        StringExpression concat=Bindings.concat(field01.textProperty(),field02.textProperty());
        TextField concatField=new TextField();
        concatField.textProperty().bind(concat);

        return new VBox(field01,field02,concatField);
    }

    public static Pane binPropertyBinBind(){
        TextField field01=new TextField();
        TextField field02=new TextField();
        field01.textProperty().bindBidirectional(field02.textProperty());

        return new VBox(field01,field02);
    }
}
