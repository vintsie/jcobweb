package invoke;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.vintsie.jcobweb.invoke.stub.IServiceStub;
import org.vintsie.jcobweb.proxy.ServiceFactory;
import org.vintsie.jcobweb.service.interfaces.CommonSV;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 3:48 PM
 */
public class TestServiceFactory {

    private transient Log log = LogFactory.getLog(TestServiceFactory.class);

    @Test
    public void testServiceFactory() throws Exception{
        CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
        log.info(commonSV.getCurrentDateTime());
        log.info(commonSV.sayHelloToWorld("Vin Tsie", 5));
    }

    @Test
    public void testRmiCall() throws Exception{
        Registry registry = LocateRegistry.getRegistry(9090);
        Remote remote = registry.lookup(IServiceStub.class.getName());
        IServiceStub ss = (IServiceStub) remote;
        ss.remoteInvoke(new HashMap<String, Object>());
    }
}
