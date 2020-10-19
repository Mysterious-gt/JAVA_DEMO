package cn.sunyog;

import cn.sunyog.bean.AnnotationBean;
import cn.sunyog.bean.AutowireBean;
import cn.sunyog.bean.XMLBean;

/**
 * @Author: jerrylee
 * @Date: 2020/9/28 3:09 下午
 * @Desc: 打印工具
 */
public class PrintTool {
    public static void printBeanInfo(XMLBean... bean) {
        for (XMLBean item : bean) {
            System.out.println(item);
        }
        for (XMLBean item : bean) {
            System.out.println(item.getMessage());
        }
    }

    public static void printBeanInfo(AnnotationBean... bean) {
        for (AnnotationBean item : bean) {
            System.out.println(item);
        }
        for (AnnotationBean item : bean) {
            System.out.println(item.getMessage());
        }
    }

    public static void printBeanInfo(AutowireBean... bean){
        for (AutowireBean item : bean) {
            XMLBean child = item.getChild();
            printBeanInfo(child);
        }
    }
}
