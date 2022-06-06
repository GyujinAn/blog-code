package designpatterns.singleton01;

public class SingletonLazyInstantiationSample02 {

    private volatile static SingletonLazyInstantiationSample02 obj;

    private SingletonLazyInstantiationSample02() {
    }

    public static SingletonLazyInstantiationSample02 getInstance(){
        if(obj == null){
            synchronized (SingletonLazyInstantiationSample02.class){
                if (obj == null){
                    obj = new SingletonLazyInstantiationSample02();
                }
            }
        }
        return obj;
    }
}
