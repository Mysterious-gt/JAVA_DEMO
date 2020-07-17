package entity;

import java.util.Optional;

/**
 * @Author: jerrylee
 * @Date: 2019/10/23 10:32 上午
 * @Desc: 测试用实体类
 */
public class Company {
    private String name;
    private String id;
    private String owner;
    private String desc;

    public Company(String name, String id, String owner) {
        this.name = name;
        this.id = id;
        this.owner = owner;
    }

    public Optional<String> getDesc(){
        return Optional.ofNullable(desc);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
