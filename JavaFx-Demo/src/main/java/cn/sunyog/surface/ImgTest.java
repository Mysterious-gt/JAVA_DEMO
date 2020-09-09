package cn.sunyog.surface;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: jerrylee
 * @Date: 2020/9/2 10:34 上午
 * @Desc: 图片
 */
public class ImgTest {
    public static Pane testImg() throws IOException {
        InputStream input = ImgTest.class.getResourceAsStream("/view/img/icon_refresh.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                double rotate = imageView.getRotate();
                imageView.setRotate(rotate+5);
            }
        },100,100);

        DialogPane pane = new DialogPane();
        pane.setGraphic(imageView);
        pane.setPadding(new Insets(20));
        //pane.setHeaderText("i am head");
        pane.setPrefSize(300, 200);
        input.close();
        return pane;
    }
}
