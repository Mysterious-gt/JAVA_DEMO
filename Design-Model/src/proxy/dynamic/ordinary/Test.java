package proxy.dynamic.ordinary;

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
    }
}
