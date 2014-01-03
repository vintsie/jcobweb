package org.vintsie.jcobweb.invoke.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 * User: caiwm
 * Date: 13-12-31
 * Time: 下午5:02
 */
public interface IServiceStub extends Remote {

    public Object remoteInvoke(HashMap<String, Object> params) throws RemoteException;

    public Object remoteInvoke(String ifc, String method, Class<?>[] argTypes, Object[] args) throws RemoteException;
}
