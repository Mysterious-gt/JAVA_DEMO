package cn.sunyog;

/**
 * @Author: jerrylee
 * @Date: 2020/10/12 9:25 上午
 * @Desc: a
 */

abstract class Product{
    public abstract void showProduct();
}

class ProductC extends Product{

    @Override
    public void showProduct() {
        System.out.println("Product C");
    }
}

class ProductD extends Product{

    @Override
    public void showProduct() {
        System.out.println("Product D");
    }
}

//抽象工厂类
public abstract class AbstractFactory {
    public abstract Product createProduct();
}

//工厂类1
class ProductCFactory extends AbstractFactory{

    @Override
    public Product createProduct() {
        return new ProductC();
    }
}

//工厂类2
class ProductDFactory extends AbstractFactory{

    @Override
    public Product createProduct() {
        return new ProductC();
    }
}

//接口工厂
interface AbstractFactoryInterface{
    public abstract Product createProduct();
}

//工厂类1
class ProductCFactoryImp implements AbstractFactoryInterface{

    @Override
    public Product createProduct() {
        return new ProductC();
    }
}

//工厂类2
class ProductDFactoryImp implements AbstractFactoryInterface{

    @Override
    public Product createProduct() {
        return new ProductD();
    }
}

//测试使用
class Client02{
    public static void main(String[] args) {
        AbstractFactoryInterface factory01=new ProductCFactoryImp();
        Product product01 = factory01.createProduct();
        product01.showProduct();

        AbstractFactoryInterface factory02=new ProductDFactoryImp();
        Product product02 = factory02.createProduct();
        product02.showProduct();
    }
}

//抽象工厂类，用于创建各种工厂
class AbstractFactoryFactory{
    public static AbstractFactory getFactory(String factory){
        switch (factory){
            case "C":
                return new ProductCFactory();
            case "D":
                return new ProductDFactory();
            default:
                return null;
        }
    }
}

class Client2{
    public static void main(String[] args) {
        //创建C产品
        Product product01=AbstractFactoryFactory.getFactory("C").createProduct();
        product01.showProduct();
        //创建D产品
        Product product02=AbstractFactoryFactory.getFactory("D").createProduct();
        product02.showProduct();
        //创建不存在的E
        Product product03=AbstractFactoryFactory.getFactory("E").createProduct();
        System.out.println(product03.toString());
    }
}