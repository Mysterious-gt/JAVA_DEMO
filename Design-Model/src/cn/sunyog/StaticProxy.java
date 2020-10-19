package cn.sunyog;

/**
 * @Author: jerrylee
 * @Date: 2020/10/12 10:07 上午
 * @Desc: a
 */
public class StaticProxy {
    public static void main(String[] args) {
        Skill zly=new StarProxy(new Star());
        zly.sing("dynamic");
        zly.perform("WestGo");
        zly.variety("ChinaJoy");
    }
}

//基础技能
interface Skill{
    void sing(String songName);
    void perform(String playName);
    void variety(String name);
}

//明星类
class Star implements Skill{

    @Override
    public void sing(String songName) {
        System.out.println("**演唱："+songName);
    }

    @Override
    public void perform(String playName) {
        System.out.println("**出演："+playName);
    }

    @Override
    public void variety(String name) {
        System.out.println("**节目："+name);
    }
}

//代理类
class StarProxy implements Skill{
    private Skill zly;

    public StarProxy(Skill zly){
        this.zly=zly;
    }

    @Override
    public void sing(String songName) {
        zly.sing(songName);
    }

    @Override
    public void perform(String playName) {
        zly.perform(playName);
    }

    @Override
    public void variety(String name) {
        zly.variety(name);
    }
}
