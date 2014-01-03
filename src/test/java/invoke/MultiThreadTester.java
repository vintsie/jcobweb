package invoke;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: dev001
 * Date: 12/27/13
 * Time: 10:51 PM
 */
public class MultiThreadTester {

    /**
     * Multithreading stress test.
     *
     * @throws Exception
     */
    @Test
    public void multiTestServiceCall() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(20, Executors.defaultThreadFactory());
        int i = 0;
        while (i < 2) {
            for (int j = 0; j < 20; j++) {
                es.execute(new RunnableCaller());
            }
            Thread.sleep(5000);
            i++;
        }
        es.shutdown();
    }
}
