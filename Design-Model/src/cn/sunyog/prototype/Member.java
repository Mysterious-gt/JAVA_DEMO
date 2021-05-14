package cn.sunyog.prototype;

/**
 * @Author: MysteriousGT
 * @Date: 2021/2/25 4:03 下午
 * @Desc:
 */
public class Member implements Cloneable{
    private int age;
    private String name;
    private double length;

    public Member(int age, String name, double length) {
        this.age = age;
        this.name = name;
        this.length = length;
    }

    @Override
    protected Member clone() throws CloneNotSupportedException {
        Member res = (Member) super.clone();
        return res;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
