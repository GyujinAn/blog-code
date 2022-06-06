package designpatterns.singleton01;

public class SingletonLazyInstantiationSample01 {
    private static SingletonLazyInstantiationSample01 obj;

    private SingletonLazyInstantiationSample01() {
    }

    public static synchronized SingletonLazyInstantiationSample01 getInstance(){
        if(obj == null){
            obj = new SingletonLazyInstantiationSample01();
        }
        return obj;
    }

}
