package cn.sunyog.surface;

import javafx.stage.Stage;

/**
 * @Author: jerrylee
 * @Date: 2020/8/24 2:08 下午
 * @Desc: 界面之 stage
 */
public class StageInit {
    public static void initStage(Stage stage){
        stage.setTitle("我是标题");

        //Stage 初始位置，相对屏幕左上角，最小值 0
        stage.setX(200);
        stage.setY(200);

        //设置 Stage 高度，宽度
        stage.setWidth(400);
        stage.setHeight(300);
        stage.setMinWidth(200);
        stage.setMaxWidth(600);

        //窗口大小是否可变，默认true，设置为false后最大化按钮不可点
        //stage.setResizable(false);

        //初始化 Stage 格式（DECORATED--默认，UNDECORATED--白色背景无装饰，TRANSPARENT--透明背景无装饰，UTILITY--始终显示在最顶端，UNIFIED--普通）
        //不是所有的平台都支持transparent模式，不支持时和undecorated没区别
        //stage.initStyle(StageStyle.TRANSPARENT);

        //其他方法
        stage.show();
    }
}
