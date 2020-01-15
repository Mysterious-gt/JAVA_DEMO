package test;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @Author: jerrylee
 * @Date: 2020/1/15 4:40 下午
 * @Desc: 数字测试
 */
public class NumTest {
    public static void main(String[] args) {
        //randomNum();
        //formatNum();

    }

    /**
     * @Desc: 数字格式化
     * @Author: Jerry
     * @Date: 2020/1/15
     * @Param: []
     * @Return: void
     */
    private static void formatNum() {
        DecimalFormat df=new DecimalFormat("####0.00");
        final String format = df.format(0.12345);
        System.out.println(format);
    }

    /**
     * @Desc: [0,100)随机数
     * @Author: Jerry
     * @Date: 2020/1/15
     * @Param: []
     * @Return: void
     */
    private static void randomNum() {
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            //生成[0,100)的随机数
            final int rand = random.nextInt(100);
            System.out.println(rand);
        }
    }
}
