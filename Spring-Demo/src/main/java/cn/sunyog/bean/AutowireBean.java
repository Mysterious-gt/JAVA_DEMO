package cn.sunyog.bean;

/**
 * @Author: jerrylee
 * @Date: 2020/9/28 3:07 下午
 * @Desc: 自动注入Bean
 */
public class AutowireBean {
    private XMLBean child;

    public AutowireBean(XMLBean child) {
        this.child = child;
        System.out.println("AutowireBean类 含参构造函数执行");
    }

    public AutowireBean() {
        System.out.println("AutowireBean类 无参构造函数执行");
    }

    public XMLBean getChild() {
        return child;
    }

    public void setChild(XMLBean child) {
        this.child = child;
        System.out.println("AutowireBean类 setter函数执行");
    }
}
