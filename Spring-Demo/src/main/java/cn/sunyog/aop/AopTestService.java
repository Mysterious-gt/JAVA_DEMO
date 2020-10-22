package cn.sunyog.aop;

import org.springframework.stereotype.Service;

/**
 * @Author: jerrylee
 * @Date: 2020/10/21 3:42 下午
 * @Desc: aop测试类
 */
@Service
public class AopTestService {
    @CutPointAnno
    public void test(){
        System.out.println("测试方法执行");
    }

    @CutPointAnno
    public void testThrowable(){
        throw new RuntimeException();
    }
}
