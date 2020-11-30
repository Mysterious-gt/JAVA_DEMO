package cn.sunyog.entity;

/**
 * @Author: MysteriousGT
 * @Date: 2020/11/25 3:51 下午
 * @Desc: person
 */
public class Person {
    private String name;
    private String add;
    private String email;
    private Double age;

    public Person(String name, String add, String email, Double age) {
        this.name = name;
        this.add = add;
        this.email = email;
        this.age = age;
    }
}
