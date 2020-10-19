package cn.sunyog;

//懒汉模式变形1：加线程锁
class Singleton03{
    private Singleton03(){}

    private static Singleton03 s;

    //防止线程问题，为公共方法加锁
    public static synchronized Singleton03 getInstance(){
        if (s==null){
            s=new Singleton03();
        }
        return s;
    }
}

/**
 * 懒汉式变形2：
 * volatile可以保证线程的可见性，但不能保证原子性
 * 即：当使用此修饰符修饰一个属性时，只有一个线程改变了此属性的值，对其它线程立即可见
 */
class Singleton04{
    private Singleton04(){}

    private static volatile Singleton04 s;

    public static Singleton04 getInstance(){
        if (s==null){//通过此项判定可以减少进入阻塞的线程数量
            synchronized (Singleton04.class){//sychronize可以保证线程操作的原子性
                if (s==null){//单例模式代码
                    s=new Singleton04();
                }
            }
        }
        return s;
    }
}

//线程单例：每个线程内部指存在一个对象
class Looper{
    private Looper(){}

    private static ThreadLocal<Looper> threadLocal=new ThreadLocal<Looper>();

    //获取当前线程的looper对象
    public static Looper getLooper(){
        Looper looper=threadLocal.get();
        if (looper==null){
            looper=new Looper();
            threadLocal.set(looper);
        }
        return looper;
    }

    //移除绑定的对象
    public void remove(){
        threadLocal.remove();
    }
}

//饿汉单例
class Singleton02{
    private Singleton02(){}

    //类初始化时即创建对象
    private static Singleton02 s=new Singleton02();

    //对象已存在，无需判断
    public static Singleton02 getInstance(){
        return s;
    }
}

/**
 * 饿汉单例变形，适用于占用内存较大，而且使用较频繁的对象
 * 其中，arrays变量用于模拟占用内存很大的对象
 */
class Singleton05{
    private Integer[] arrays=new Integer[2048];

    private Singleton05(){}

    //静态内部类，构建对象，访问类属性，获取方法时，会加载
    static class Inner{
        private static Singleton05 s=new Singleton05();
    }

    public static Singleton05 getInstance(){
        return Inner.s;
    }

    //频繁访问这歌方法时不会加载静态内部类中的代码
    public static void doSoimething(){
        System.out.println("do");
    }
}

/**
 * 特殊的单例模式：枚举类型
 * 应用场景：用于定义固定的一些常量。如：一年四季
 */
enum Singleton06{
    //单例对象，类加载时创建，且只创建一个
    instance;
    public void doSomething(){
        System.out.println("do");
    }
}
