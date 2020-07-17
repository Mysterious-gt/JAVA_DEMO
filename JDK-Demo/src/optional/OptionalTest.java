package optional;

import entity.Company;

import java.util.UUID;

/**
 * @Author: jerrylee
 * @Date: 2019/10/23 10:28 上午
 * @Desc: 测试optional类
 */
public class OptionalTest {

    public static void main(String[] args) {
        System.out.println("=====optional对象非空操作======");
        OptionalService.presentDo(null);
        Company p = new Company("Tencent", UUID.randomUUID().toString(), "Jack");
        OptionalService.presentDo(p);

        System.out.println("=====optional对象过滤操作========");
        OptionalService.filterDo(p);
        Company p2 = new Company("Alibaba", UUID.randomUUID().toString(), "Tony");
        OptionalService.filterDo(p2);

        System.out.println("=======optional空对象操作=======");
        OptionalService.emptOptionDo(null);
        //OptionalService.emptOptionThrow(null);

        System.out.println("======optional对象转换=========");
        OptionalService.optionMapDo(p);

        System.out.println("======optional对象flatMap方法转换========");
        p.setDesc("描述");
        p2.setDesc("DESC:描述");
        OptionalService.optionFlatMapDo(p);
        OptionalService.optionFlatMapDo(p2);
    }
}
