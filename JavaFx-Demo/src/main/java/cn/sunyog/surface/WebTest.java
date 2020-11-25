package cn.sunyog.surface;

import cn.sunyog.entity.ABCD;
import javafx.concurrent.Worker;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * @Author: jerrylee
 * @Date: 2020/9/10 3:27 下午
 * @Desc: web组件
 */
public class WebTest {
    private static ABCD app;
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
        browser.load(url);

        //java和javascript互相调用时必须使用这种形式，等html加载完成后再调用
        //html未加载完成时不存在javascript方法，直接报错
        browser.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue== Worker.State.SUCCEEDED){
                //js调用java,必须是public方法，public类
                //其中的app对象，必须是全局变量，否则当再一次gc时，app对象会被回收，则js无法获取到app对象，sout（）方法无法执行
                JSObject window = (JSObject) browser.executeScript("window");
                app=new ABCD();
                window.setMember("app", app);

                //java调用js
                browser.executeScript("func('I AM A H1 DOM');");
            }
        });
        pane.getChildren().add(web);

        return pane;
    }

}
