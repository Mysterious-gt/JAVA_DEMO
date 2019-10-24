package proxy.dynamic.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @Author: jerrylee
 * @Date: 2019/10/23 4:57 下午
 * @Desc: callbackFilter实现类，用于确定代理类的优先级
 */
public class MapperFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if ("insert".equals(method.getName())){
            return 0;
        }
        return 1;
    }
}
