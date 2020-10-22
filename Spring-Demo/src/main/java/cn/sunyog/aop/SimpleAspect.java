package cn.sunyog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: jerrylee
 * @Date: 2020/10/21 11:13 上午
 * @Desc: 切面
 */
@Aspect
@Component
public class SimpleAspect {
    @Pointcut("@annotation(cn.sunyog.aop.CutPointAnno)")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("前置通知");
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("后置通知");
    }

    @Around("pointCut()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知，前段");
        joinPoint.proceed();//程序执行
        System.out.println("环绕通知，后段");
    }

    @AfterThrowing("pointCut()")
    public void doThrowing(JoinPoint joinPoint){
        System.out.println("异常通知");
    }
}
