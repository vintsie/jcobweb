package invoke;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.proxy.ServiceFactory;
import org.vintsie.jcobweb.service.interfaces.CommonSV;

/**
 * User: dev001
 * Date: 12/27/13
 * Time: 10:47 PM
 */
public class RunnableCaller implements Runnable {
    @Override
    public void run() {
        try{
            CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
            commonSV.sayHelloToWorld(Thread.currentThread().getName(), 1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
