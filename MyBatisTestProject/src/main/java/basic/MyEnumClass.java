package basic;

public enum MyEnumClass {
    IINSTANCE("some value for the string."),
    TESTT("Another value Of String");

    private final String someString;

    private MyInstance instance;

    private MyEnumClass(final String someString) {
        this.someString = someString;
        if (null != this.instance) {
            this.instance = new MyInstance();
        }
    }

    public String getSomeString(){
        return this.someString;
    }

    public MyInstance getInstance() {
        return this.instance;
    }

    private class MyInstance {

        public MyInstance() {
            System.out.println("单例被初始化成功！");
        }

    }

}
