package proxy.dynamic.ordinary;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: jerrylee
 * @Date: 2019/10/21 4:12 下午
 * @Desc: cglib动态代理类
 */
public class DemoProxyCGLIB implements MethodInterceptor {
    //别代理对象
    private Object target;

    public Object getInstance(Object target){
        this.target=target;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理开始！");
        final Object result = methodProxy.invokeSuper(target, objects);
        System.out.println("cglib动态代理完毕！");
        return result;
    }
}
