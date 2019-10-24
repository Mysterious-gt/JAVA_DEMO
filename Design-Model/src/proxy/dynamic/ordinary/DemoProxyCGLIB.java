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
    //被代理对象
    private Object target;

    /**
     * @Desc: 获取被代理类/接口实例
     * @Author: Jerry
     * @Date: 2019/10/23
     * @Param: [target]
     * @Return: java.lang.Object
     */
    public Object getInstance(Object target){
        this.target=target;
        Enhancer enhancer=new Enhancer();
        //需要被代理的类
        enhancer.setSuperclass(target.getClass());
        //回调，调用cglib代理的intercept方法
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * @Desc: 回调方法
     * @Author: Jerry
     * @Date: 2019/10/23
     * @Param: [o, method, objects, methodProxy]
     * @Return: java.lang.Object
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib动态代理开始！");
        final Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("cglib动态代理完毕！");
        return result;
    }
}
