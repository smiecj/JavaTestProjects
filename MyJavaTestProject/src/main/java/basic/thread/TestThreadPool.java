package basic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author shuaifeng
 * @Since 2019/8/17
 */
public class TestThreadPool {

    public static void main(String[] args) {
        testShutdownTwice();
    }

    public static void testShutdownTwice() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.shutdownNow();
        service.shutdown();
    }

}
