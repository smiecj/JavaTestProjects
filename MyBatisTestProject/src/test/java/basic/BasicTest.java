package basic;

import org.junit.Test;

public class BasicTest {

    @Test
    public void initializeEnumClass() {
        MyEnumClass instance = MyEnumClass.IINSTANCE;
        System.out.println(instance.getSomeString());
    }

}
