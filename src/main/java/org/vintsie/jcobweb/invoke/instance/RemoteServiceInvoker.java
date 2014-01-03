package org.vintsie.jcobweb.invoke.instance;

import org.vintsie.jcobweb.invoke.stub.IServiceStub;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Remote service invoker
 *
 * User: dev001
 * Date: 12/26/13
 * Time: 1:16 PM
 */
public class RemoteServiceInvoker extends IServiceInvoker {


    /**
     * set the call type of service invoke to
     * <code>IServiceInvoker.callType_Remote</code>.
     */
    public RemoteServiceInvoker(){
        setCallType(callType_Remote);
    }

    @Override
    public Object instanceServiceObject(Class<?> clazz) throws Exception {
        return instanceServiceObject("");
    }

    @Override
    public Object instanceServiceObject(String className) throws Exception {

        // local rmi server
        Registry registry = LocateRegistry.getRegistry("10.11.16.60", 9090);
        IServiceStub remoteStub = (IServiceStub)registry.lookup(IServiceStub.class.getName());

        if(log.isInfoEnabled()){
            log.info("Remote Service stub will return, wrapped by " + className);
        }
        return remoteStub;
    }
}
