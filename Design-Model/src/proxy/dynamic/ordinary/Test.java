package proxy.dynamic.ordinary;

import proxy.dynamic.cglib.CglibMapperImpl;
import proxy.dynamic.cglib.CglibMapperProxy;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 4:00 下午
 * @Desc: 测试类
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("=====jdk动态代理======");
        DemoProxy handler=new DemoProxy();
        final DemoMapper proxy = (DemoMapper) handler.bind(new DemoMapperImpl());
        proxy.sayHello();

        System.out.println("======cglib动态代理======");
        DemoProxyCGLIB cglib=new DemoProxyCGLIB();
        final DemoMapperImpl impl = (DemoMapperImpl) cglib.getInstance(new DemoMapperImpl());
        impl.sayHello();

        System.out.println("====cglib动态代理优先级====");
        final CglibMapperProxy proxy1 = new CglibMapperProxy();
        final CglibMapperImpl instance = (CglibMapperImpl) proxy1.getInstance(new CglibMapperImpl());
        instance.insert();
        instance.select();
    }
}
