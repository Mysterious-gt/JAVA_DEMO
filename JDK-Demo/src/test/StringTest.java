package test;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: jerrylee
 * @Date: 2020/1/15 1:49 下午
 * @Desc: string字符串测试
 */
public class StringTest {
    public static void main(String[] args) {
        //testStrMethod();

    }

    private static void testStrMethod() {
        String s = "0";
        String a = "abcd0000ABC.@9000";
        String text = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(a.toUpperCase());
        System.out.println(s.toUpperCase());

        //特殊字符需转译
        final String[] split = a.split("\\.");
        System.out.println(Arrays.toString(split));
    }
}
