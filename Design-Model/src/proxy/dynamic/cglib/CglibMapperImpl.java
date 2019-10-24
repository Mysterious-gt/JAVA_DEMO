package proxy.dynamic.cglib;

/**
 * @Author: jerrylee
 * @Date: 2019/10/23 4:45 下午
 * @Desc: cglib测试用实现类
 */
public class CglibMapperImpl implements CglibMapper {
    @Override
    public void insert() {
        System.out.println("insert method do");
    }

    @Override
    public void select() {
        System.out.println("select method do");
    }
}
