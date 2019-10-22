package proxy.dynamic.ordinary;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 3:52 下午
 * @Desc: 动态代理类
 */
public class DemoProxy implements InvocationHandler {
    //被代理的对象
    private Object target;

    public Object bind(Object target){
        this.target=target;
        //获取代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理开始");
        final Object result = method.invoke(target, args);
        System.out.println("jdk动态代理结束");
        return result;
    }
}
