package cn.sunyog.surface;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.InputStream;
import java.net.URL;

/**
 * @Author: jerrylee
 * @Date: 2020/9/10 3:27 下午
 * @Desc: web组件
 */
public class WebTest {
    public static Pane testWebUrl(){
        Pane pane=new FlowPane();
        WebView web = new WebView();
        WebEngine browser = web.getEngine();
        browser.load("http://www.baidu.com");
        pane.getChildren().add(web);

        return pane;
    }

    public static Pane testHTMLJS(){
        Pane pane=new FlowPane();
        pane.setPrefSize(100,200);
        WebView web=new WebView();
        WebEngine browser = web.getEngine();
        String url = WebTest.class.getResource("/view/html/test.html").toExternalForm();
        browser.loadContent(url);

        //browser.executeScript("func('测试弹窗方法')");
        return pane;
    }
}
