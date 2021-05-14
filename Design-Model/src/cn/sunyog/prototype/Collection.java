package cn.sunyog.prototype;

/**
 * @Author: MysteriousGT
 * @Date: 2021/2/25 4:04 下午
 * @Desc:
 */
public class Collection implements Cloneable {
    private Member member;
    private int id;

    public Collection(Member member, int id) {
        this.member = member;
        this.id = id;
    }

    @Override
    protected Collection clone() throws CloneNotSupportedException {
        Collection res = (Collection) super.clone();
        res.setMember(this.getMember().clone());
        return res;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
