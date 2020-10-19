package cn.sunyog;

/**
 * @Author: jerrylee
 * @Date: 2020/10/12 9:06 上午
 * @Desc: aa
 */
public class SimpleFactory {
}

//抽象产品类
abstract class AbstractProduct{
    public abstract void showProduct();
}
//具体产品类A
class ProductA extends AbstractProduct{

    @Override
    public void showProduct() {
        System.out.println("Product A");
    }
}
//具体产品类B
class ProductB extends AbstractProduct{

    @Override
    public void showProduct() {
        System.out.println("Product B");
    }
}

//工厂类
class ProductFactory{
    public static AbstractProduct createProduct(String name){
        switch (name){
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                return null;
        }
    }
}

//测试
class Client{
    public static void main(String[] args) {
        AbstractProduct product;
        try {
            product=ProductFactory.createProduct("A");
            product.showProduct();
        }catch (Exception e){
            System.out.println("No A");
        }
    }
}

