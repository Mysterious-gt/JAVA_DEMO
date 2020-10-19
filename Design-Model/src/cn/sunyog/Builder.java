package cn.sunyog;

/**
 * @Author: jerrylee
 * @Date: 2020/10/10 3:17 下午
 * @Desc: aa
 */
public class Builder {
    public static void main(String[] args) {
        ComputerBuilder builder = new MacBookBuilder();
        Director director = new Director(builder);
        director.construct("intel","sunsong");
        System.out.println(builder.create().toString());
    }
}

abstract class Computer{
    protected String board;
    protected String display;
    protected String os;

    protected Computer() {
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public abstract void setOs();

    @Override
    public String toString() {
        return "Computer{" +
                "board='" + board + '\'' +
                ", display='" + display + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}

class MacComputer extends Computer{
    @Override
    public void setOs() {
        os="MAC OSX 10.10";
    }
}

abstract class ComputerBuilder{
    public abstract void builderBoard(String board);

    public abstract void builderDisplay(String display);

    public abstract void buildOS();

    public abstract Computer create();
}

class MacBookBuilder extends ComputerBuilder{
    private Computer computer=new MacComputer();
    @Override
    public void builderBoard(String board) {
        computer.setBoard(board);
    }

    @Override
    public void builderDisplay(String display) {
        computer.setDisplay(display);
    }

    @Override
    public void buildOS() {
        computer.setOs();
    }

    @Override
    public Computer create() {
        return computer;
    }
}

class Director{
    ComputerBuilder builder=null;

    public Director(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void construct(String board,String display){
        builder.builderBoard(board);
        builder.builderDisplay(display);
        builder.buildOS();
    }
}


