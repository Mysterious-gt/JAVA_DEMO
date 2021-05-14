package cn.sunyog.entity;

import cn.sunyog.surface.ColumnIndex;

/**
 * @Author: MysteriousGT
 * @Date: 2020/11/25 3:51 下午
 * @Desc: person
 */
public class Person {
    @ColumnIndex(value = 0)
    private String name;
    @ColumnIndex(value = 1)
    private String add;
    @ColumnIndex(value = 2)
    private String email;
    @ColumnIndex(value = 3)
    private Double age;

    public Person(String name, String add, String email, Double age) {
        this.name = name;
        this.add = add;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}
