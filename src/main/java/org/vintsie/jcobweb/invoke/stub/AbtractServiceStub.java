package org.vintsie.jcobweb.invoke.stub;

import org.apache.commons.lang3.StringUtils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * User: dev001
 * Date: 12/26/13
 * Time: 2:38 PM
 */
public abstract class AbtractServiceStub extends UnicastRemoteObject implements IServiceStub {
    protected static String REST_out_empty = "";

    /**
     * Handle RemoteException when service stub is constructed.
     *
     * @throws java.rmi.RemoteException
     */
    public AbtractServiceStub() throws RemoteException{
        super();
    }

    /**
     * add REST interface support to make life easier.
     *
     * @param REST_in   RESTful param
     * @param REST_type define RESTful type, xml or json
     *
     * @return RESTful return
     * @throws java.rmi.RemoteException
     */
    public String remoteInvoke(String REST_in, String REST_type) throws RemoteException{
        if(StringUtils.equals(REST_type, "xml")){
            return remoteInvokeXmlRestfully(REST_in);
        }

        if(StringUtils.equals(REST_type, "json")){
            return remoteInvokeJsonRestfully(REST_in);
        }
        return  REST_out_empty;
    }

    /**
     * call Xml-RESTful handler
     *
     * @param xml   REST_in
     * @return  RESTful result
     *
     * @throws java.rmi.RemoteException
     */
    public String remoteInvokeXmlRestfully(String xml) throws RemoteException{
        return REST_out_empty;
    }

    /**
     * call Json-RESTful handler
     *
     * @param jsonStr   REST_in
     * @return  REST result
     *
     * @throws java.rmi.RemoteException
     */
    public String remoteInvokeJsonRestfully(String jsonStr) throws RemoteException{
        return REST_out_empty;
    }

}
