package cn.sunyog.prototype;

/**
 * @Author: MysteriousGT
 * @Date: 2021/2/25 4:05 下午
 * @Desc:
 */
public class ProtoDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Member zhangf = new Member(12, "张飞", 12.33);
        Collection coll = new Collection(zhangf, 1);

        Collection coll2 = (Collection) coll.clone();

        String str1="collect01\tid="+coll.getId()+"\t"+coll.getMember()+"\tmem="+coll;
        String str2="collect02\tid="+coll2.getId()+"\t"+coll2.getMember()+"\tmem="+coll2;

        System.out.println(str1);
        System.out.println(str2);
        System.out.println("=============");
        coll.getMember().setName("刘备");
        System.out.println("coll1`s name is "+coll.getMember().getName());
        System.out.println("coll2`s name is "+coll2.getMember().getName());
    }
}
