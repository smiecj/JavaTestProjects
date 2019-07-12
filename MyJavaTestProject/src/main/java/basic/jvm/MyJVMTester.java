package basic.jvm;

import java.util.HashMap;

public class MyJVMTester {

    private static final String BASIC_STR = "test";

    public static void main(String[] args) {
        // 测试栈溢出
//       testStackOverflow(0);
        testHeapOverflow();
    }

    /**
     * -Xmx=16m
     * @param depth
     */
    public static void testStackOverflow(int depth) {
        depth++;
        System.out.println("当前栈的深度为：" + depth);
        testStackOverflow(depth);
    }

    // 测试堆溢出
    public static void testHeapOverflow() {
        long i = 1;
        String oldStr = BASIC_STR;
        while (true) {
            String newStr = oldStr + oldStr + i++;
            oldStr = newStr;
            System.out.println(i + newStr);
        }
    }

}
