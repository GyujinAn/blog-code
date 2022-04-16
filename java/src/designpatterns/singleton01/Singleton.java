package designpatterns.singleton01;

/**
 * @author agj017@gmail.com
 * @since 2022/04/16
 */
public class Singleton {

    volatile private static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getInstance(){

        if(uniqueInstance == null){
            synchronized(Singleton.class){
                if (uniqueInstance == null)
                    uniqueInstance = new Singleton();
            }
        }

        return uniqueInstance;
    }


}
