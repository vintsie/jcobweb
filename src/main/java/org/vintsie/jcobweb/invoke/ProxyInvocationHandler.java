package org.vintsie.jcobweb.invoke;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.invoke.instance.IServiceInvoker;
import org.vintsie.jcobweb.invoke.stub.IServiceStub;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 5:20 PM
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private transient static Log log = LogFactory.getLog(ProxyInvocationHandler.class);

    private Object instance;
    private Class iClass;

    public ProxyInvocationHandler(Object instance, Class<?> iClass){
        this.instance = instance;
        this.iClass = iClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("toString".equals(method.getName())) {
            return "Proxy@" + iClass.getName();
        }

        Object rtn = null;
        StringBuilder logInfo = new StringBuilder();

        if (IServiceInvoker.callType_Local.equals(ServiceFactory.getServiceInvokeType())) {
            if (log.isDebugEnabled()) {
                logInfo.append("Method: ").append(method.getName()).append(", ParameterTypes: ")
                        .append(Arrays.toString(method.getParameterTypes()))
                        .append(", is starting to invoke on ").append(this.iClass.getName());
                log.debug(logInfo.toString());
            }
            rtn = method.invoke(this.instance, args);
        }
        if (IServiceInvoker.callType_Remote.equals(ServiceFactory.getServiceInvokeType())) {
            IServiceStub remoteInstance = (IServiceStub) instance;
            rtn = remoteInstance.remoteInvoke(
                    this.iClass.getName(), method.getName(), method.getParameterTypes(), args);
        }
        return rtn;
    }
}
