package cn.sunyog.bean;

/**
 * @Author: jerrylee
 * @Date: 2020/9/27 11:09 上午
 * @Desc: 基于注解配置的Bean
 */
public class AnnotationBean {
    private String message;

    public AnnotationBean() {
        System.out.println("构造："+this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void initFunc(){
        System.out.println("注解配置的初始化方法");
    }

    public void destroyFunc(){
        System.out.println("注解配置的销毁方法");
    }
}
