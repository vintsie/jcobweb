package invoke;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: dev001
 * Date: 12/27/13
 * Time: 10:51 PM
 */
public class MultiThreadTester {
    //private transient static Log log = LogFactory.getLog(MultiThreadTester.class);

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(20, Executors.defaultThreadFactory());
        int i = 0;
        while (i < 2) {
            //es.
            for (int j = 0; j < 20; j++) {
                es.execute(new RunnableCaller());
            }
            Thread.sleep(5000);
            i++;
        }
        es.shutdown();
    }
}
