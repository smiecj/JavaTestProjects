package basic;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * @Author shuaifeng
 * @Since 2019/8/12
 */
public class ConcurrentTest {

    @Test
    public void testSynchronousLock() throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        queue.put("123");
    }

}
