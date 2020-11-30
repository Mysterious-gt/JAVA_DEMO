package cn.sunyog.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @Author: MysteriousGT
 * @Date: 2020/11/25 3:07 下午
 * @Desc: 表格元素
 */
public class TableItem {
    private SimpleStringProperty name;
    private SimpleStringProperty add;
    private SimpleStringProperty email;
    private SimpleDoubleProperty age;

    public TableItem(String name, String add, String email, Double age) {
        this.name=new SimpleStringProperty(name);
        this.add=new SimpleStringProperty(add);
        this.email=new SimpleStringProperty(email);
        this.age=new SimpleDoubleProperty(age);
    }

    public TableItem() {
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAdd() {
        return add.get();
    }

    public SimpleStringProperty addProperty() {
        return add;
    }

    public void setAdd(String add) {
        this.add.set(add);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public double getAge() {
        return age.get();
    }

    public SimpleDoubleProperty ageProperty() {
        return age;
    }

    public void setAge(double age) {
        this.age.set(age);
    }
}
