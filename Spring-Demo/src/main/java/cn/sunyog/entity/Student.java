package cn.sunyog.entity;

/**
 * @Author: jerrylee
 * @Date: 2020/10/19 10:01 上午
 * @Desc: 学生类
 */
public class Student {
    private Integer sId;
    private String sName;
    private String sGrade;
    private String sClass;
    private Integer sAge;
    private Integer sGender;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public Integer getsAge() {
        return sAge;
    }

    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    public Integer getsGender() {
        return sGender;
    }

    public void setsGender(Integer sGender) {
        this.sGender = sGender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", sGrade='" + sGrade + '\'' +
                ", sClass='" + sClass + '\'' +
                ", sAge=" + sAge +
                ", sGender=" + sGender +
                '}';
    }
}
