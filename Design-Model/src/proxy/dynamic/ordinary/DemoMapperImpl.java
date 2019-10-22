package proxy.dynamic.ordinary;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 3:51 下午
 * @Desc: 动态代理实现类
 */
public class DemoMapperImpl implements DemoMapper {
    @Override
    public void sayHello() {
        System.out.println("Hello World !!");
    }
}
