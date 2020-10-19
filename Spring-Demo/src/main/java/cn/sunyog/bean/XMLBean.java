package cn.sunyog.bean;

/**
 * @Author: jerrylee
 * @Date: 2020/9/23 2:03 下午
 * @Desc: 测试类
 */
public class XMLBean{
    private String message;

    public XMLBean() {
        System.out.println("构造："+this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void initFunc(){
        System.out.println("Bean 初始化方法执行");
    }

    public void destroyFund(){
        System.out.println("Bean 销毁方法执行");
    }
}
