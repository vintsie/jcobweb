package org.vintsie.jcobweb.framework;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.invoke.stub.IServiceStub;
import org.vintsie.jcobweb.invoke.stub.RmiServiceStub;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * User: dev001
 * Date: 12/26/13
 * Time: 4:03 PM
 */
public class RmiFrameWork {

    private transient static Log log = LogFactory.getLog(RmiFrameWork.class);

    public static void main(String[] args) throws Exception{

        //System.setSecurityManager(new RMISecurityManager());

        //Registry registry = LocateRegistry.getRegistry("192.168.223.134", 9090);
        //if(null == registry){
        //   registry = LocateRegistry.createRegistry(9090);
        //}
        //if(null == registry.list() || registry.list().length < 1){
        //ClassLoader.getSystemClassLoader().loadClass()

        //load service factory
        if(log.isInfoEnabled()){
            log.info("Set System Service Invoke Factory:" +
                    ServiceFactory.getServiceInvoke().getClass().getName());
            log.info("Set System Service Invoke Type:" + ServiceFactory.getServiceInvokeType());
        }
        //ServiceFactory.getServiceInvokeType();
        Registry registry = LocateRegistry.createRegistry(9090);
        //AbtractServiceStub ss = (AbtractServiceStub) UnicastRemoteObject.exportObject(new RmiServiceStub(), 0);
        //registry.
        registry.rebind(IServiceStub.class.getName(), new RmiServiceStub());
        if(log.isInfoEnabled()){
            log.info("RMI server is ready.");
        }
        //log.info(registry.list());

       // Registry registry1 = LocateRegistry.getRegistry(java.net.InetAddress.getLocalHost().getHostAddress(), 9090);
        //log.info(registry1.list().length);
       // }
    }

}
