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
    private transient static Log log = LogFactory.getLog(RunnableCaller.class);

    @Override
    public void run() {
        try{
            //Thread.currentThread().setName("JCobweb" + Thread.currentThread().getName());
            CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
            //if(log.isDebugEnabled()){
            //    log.debug(commonSV.sayHelloToWorld(Thread.currentThread().getName(), 1));
            //}
            commonSV.sayHelloToWorld(Thread.currentThread().getName(), 1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
