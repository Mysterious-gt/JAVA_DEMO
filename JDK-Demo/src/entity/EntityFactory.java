package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: jerrylee
 * @Date: 2020/1/21 9:10 上午
 * @Desc: 用于生产测试用实体类
 */
public class EntityFactory {
    //默认list容量
    private final int DEFAULT_LIST_CAPCITY = 10;
    private final String[] DEFAULT_NAMES = {"老刘", "程心", "章北海", "艾AA", "罗辑", "云天明", "史强", "韦德", "质子", "杨冬"};

    public List<Student> createStuList() {
        List<Student> result = new ArrayList<>(DEFAULT_LIST_CAPCITY);
        Random random = new Random();
        for (int i = 0; i < DEFAULT_LIST_CAPCITY; i++) {
            Student stu = new Student();
            stu.setId(i);
            stu.setGrade(random.nextInt(9) + 1);
            stu.setGender(i % 2 == 0 ? "男" : "女");
            stu.setName(DEFAULT_NAMES[i]);
            result.add(stu);
        }
        return result;
    }
}
