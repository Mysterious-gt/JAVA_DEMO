package test;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: jerrylee
 * @Date: 2020/1/15 1:49 下午
 * @Desc: string字符串测试
 */
public class StringTest {
    public static void main(String[] args) {
        //testStrMethod();
        String str="java提取aa汉子11111";
        Pattern pattern=Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(str);
        String res = matcher.replaceAll("");

        Pattern pattern1=Pattern.compile("[^\\u4e00-\\u9fa5]");
        Matcher matcher1 = pattern1.matcher(str);
        String res1 = matcher1.replaceAll("");

        System.out.println(str);
        System.out.println(res);
        System.out.println(res1);
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
