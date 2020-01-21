package stream;

import entity.EntityFactory;
import entity.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: jerrylee
 * @Date: 2020/1/21 9:05 上午
 * @Desc: 测试stream相关api（jdk1.8之后的版本）
 */
public class StreamAPI {
    public static void main(String[] args) {
        EntityFactory factory = new EntityFactory();
        final List<Student> stus = factory.createStuList();

        mapAPITest(stus);
        //filterAPITest(stus);
        //sortAPITest(stus);
        //minAPITest(stus);
        //matchAPITest(stus);
        //distinctAPITest();
    }
    /**
     * @Desc: 去重
     * @Author: Jerry
     * @Date: 2020/1/21
     * @Param: []
     * @Return: void
     */
    private static void distinctAPITest() {
        String[] strs={"111","222","111","222"};
        final List<String> strList = Arrays.asList(strs);
        System.out.println("去重前长度："+strList.size());
        final List<String> strsDis = strList.stream().distinct().collect(Collectors.toList());
        System.out.println("去重后长度："+strsDis.size());
    }

    /**
     * @Desc: 验证是否匹配
     * @Author: Jerry
     * @Date: 2020/1/21
     * @Param: [stus]
     * @Return: void
     */
    private static void matchAPITest(List<Student> stus) {
        final boolean anyMatch = stus.stream().anyMatch(item -> item.getGrade() < 5);
        if (anyMatch){
            System.out.println("有低于5年级的");
        }
        final boolean allMatch = stus.stream().allMatch(item -> item.getGrade() > 0);
        if (allMatch){
            System.out.println("都高于0年级");
        }
        final boolean noneMatch = stus.stream().noneMatch(item -> item.getGrade() > 10);
        if (noneMatch){
            System.out.println("没有高于10年级的");
        }
    }

    /**
     * @Desc: min()方法，获取最小值，max()方法同理
     * @Author: Jerry
     * @Date: 2020/1/21
     * @Param: [stus]
     * @Return: void
     */
    private static void minAPITest(List<Student> stus) {
        final Student min = stus.stream()
                .min((s1, s2) -> Integer.compare(s1.getGrade() + s1.getId(), s2.getGrade() + s2.getId()))
                .get();
        System.out.println(min.toString());
    }

    /**
     * @Desc: sort()方法，用于排序。s1-s2生序，s2-s1降序
     * @Author: Jerry
     * @Date: 2020/1/21
     * @Param: [stus]
     * @Return: void
     */
    private static void sortAPITest(List<Student> stus) {
        stus.stream().sorted((s1,s2)->s2.getGrade()-s1.getGrade()).forEach(item-> System.out.println(item.toString()));
    }

    /**
     * @Desc: filter()方法用于过滤，剩下list中符合某种条件的对象
     * @Author: Jerry
     * @Date: 2020/1/21
     * @Param: [stus]
     * @Return: void
     */
    private static void filterAPITest(List<Student> stus) {
        stus.stream().filter(item -> "女".equals(item.getGender())).forEach(i -> System.out.println(i.toString()));
    }

    /**
     * @Desc: map()方法，用于转换list中的元素
     * @Author: Jerry
     * @Date: 2020/1/21
     * @Param: [stus]
     * @Return: void
     */
    private static void mapAPITest(List<Student> stus) {
        stus.stream().map(item -> {
            final String newName = item.getName() + "(" + item.getGender() + ")";
            item.setName(newName);
            return item;
        }).forEach(s -> System.out.println(s.toString()));
    }
}
