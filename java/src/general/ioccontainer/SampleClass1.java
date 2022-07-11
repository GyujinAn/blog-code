package general.ioccontainer;

public class SampleClass1 {

    SampleClass2 dependency;

    public SampleClass1() {
    }

    public SampleClass1(SampleClass2 dependency) {
        this.dependency = dependency;
    }

    public void setDependency(SampleClass2 dependency) {
        this.dependency = dependency;
    }
}
