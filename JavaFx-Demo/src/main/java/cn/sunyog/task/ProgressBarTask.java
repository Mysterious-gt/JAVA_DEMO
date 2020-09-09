package cn.sunyog.task;

import javafx.concurrent.Task;

/**
 * @Author: jerrylee
 * @Date: 2020/9/7 3:34 下午
 * @Desc:
 */
public class ProgressBarTask extends Task {
    @Override
    protected Object call() throws Exception {
        for (int i = 0; i < 10; i++) {
            long time = System.currentTimeMillis();
            String msg="当前时间戳：" + time;
            System.out.println(msg);


            this.updateMessage(msg);
            Thread.sleep(1000);
        }

        return null;
    }
}
