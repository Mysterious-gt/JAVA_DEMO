package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: jerrylee
 * @Date: 2020/1/15 1:31 下午
 * @Desc: calender日期测试
 */
public class CalenderTest {
    private static Calendar c=Calendar.getInstance();

    private static final SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        //获取当前日期，月份（从0开始），小时数
        final int date = c.get(Calendar.DATE);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.HOUR_OF_DAY);
        System.out.println(date+" "+month+" "+day);

        //月份从0开始
        c.set(2019,1,12);
        final Date cDate = c.getTime();
        final String sdfDate = SDF.format(cDate);
        System.out.println(sdfDate+":"+cDate);

        //日期计算
        c.add(Calendar.MONTH,2);
        System.out.println(SDF.format(c.getTime()));
        //日期比较
        System.out.println(c.before(Calendar.getInstance()));

        c.clear();
    }
}
