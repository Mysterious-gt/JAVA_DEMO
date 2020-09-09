package cn.sunyog.surface;

import cn.sunyog.task.ProgressBarTask;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @Author: jerrylee
 * @Date: 2020/9/7 2:55 下午
 * @Desc: 进度条相关测试
 */
public class ProgressTest {
    public static Pane testProgres(){
        FlowPane pane=new FlowPane();
        pane.setPadding(new Insets(20));
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPrefSize(200,100);

        Button btn=new Button("测试进度条");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btn.setDisable(true);
                FlowPane inner=new FlowPane();
                inner.setPrefSize(200,400);
                inner.setAlignment(Pos.CENTER);
                inner.setVgap(10);
                inner.setHgap(10);
                inner.setPadding(new Insets(20));
                ProgressBar indicator=new ProgressBar();
                //ProgressIndicator indicator=new ProgressIndicator();
                Label label=new Label();
                label.textProperty().unbind();
                indicator.progressProperty().unbind();
                //设置后台执行任务
                Task task=new ProgressBarTask();
                indicator.progressProperty().bind(task.progressProperty());
                label.textProperty().bind(task.messageProperty());
                inner.getChildren().addAll(indicator,label);

                Scene scene=new Scene(inner);
                Stage stage=new Stage(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();

                //完成后执行
                task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        stage.close();
                        btn.setDisable(false);
                    }
                });
                //开始执行
                new Thread(task).start();
            }
        });

        pane.getChildren().addAll(btn);

        return pane;
    }
}
