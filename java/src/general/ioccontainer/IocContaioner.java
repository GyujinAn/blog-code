package general.ioccontainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IocContaioner {

    Map<String, Object> beans = new HashMap<>();

    public IocContaioner() {
        createBeans();
        injectDependency();
    }

    private void createBeans() {

        try {
            Class<SampleClass1> sampleClass1Class = SampleClass1.class;
            String name1 = sampleClass1Class.getName();
            System.out.println(name1);
            beans.put(name1, sampleClass1Class.getDeclaredConstructor().newInstance());

            Class<SampleClass2> sampleClass2Class = SampleClass2.class;
            String name2 = sampleClass2Class.getName();
            System.out.println(name2);
            beans.put(name2, sampleClass2Class.getDeclaredConstructor().newInstance());

        }catch (Exception e){

        }
    }

    private void injectDependency(){
        SampleClass1 bean1 = (SampleClass1) getBean("general.ioccontainer.SampleClass1");
        SampleClass2 bean2 = (SampleClass2) getBean("general.ioccontainer.SampleClass2");
        bean1.setDependency(bean2);
    }

    public Object getBean(String beanName){
        return beans.get(beanName);
    }


}
