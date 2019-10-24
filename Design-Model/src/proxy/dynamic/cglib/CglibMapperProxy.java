package proxy.dynamic.cglib;

import net.sf.cglib.proxy.*;
import proxy.dynamic.ordinary.DemoProxyCGLIB;

import java.lang.reflect.Method;

/**
 * @Author: jerrylee
 * @Date: 2019/10/23 4:48 下午
 * @Desc: cglib代理类1,匹配代理拦截方式
 */
public class CglibMapperProxy implements MethodInterceptor {
    public Object getInstance(Object target){
        Enhancer hancer=new Enhancer();
        hancer.setSuperclass(target.getClass());
        hancer.setCallbacks(new Callback[]{this, new DemoProxyCGLIB(), NoOp.INSTANCE});
        hancer.setCallbackFilter(new MapperFilter());

        //设置select()方法在构造方法中执行时不被拦截，默认为ture（拦截）
        hancer.setInterceptDuringConstruction(false);
        return hancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        final Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}
