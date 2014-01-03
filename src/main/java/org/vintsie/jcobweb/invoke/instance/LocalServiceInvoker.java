package org.vintsie.jcobweb.invoke.instance;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/24/13
 * Time: 12:34 PM
 */
public class LocalServiceInvoker extends IServiceInvoker {

    //private transient static Log log = LogFactory.getLog(LocalServiceInvoker.class);

    public LocalServiceInvoker(){
        setCallType(callType_Local);
    }

    @Override
    public Object instanceServiceObject(Class<?> clazz) throws Exception{
        return clazz.newInstance();
    }

    @Override
    public Object instanceServiceObject(String className) throws Exception{
        return this.instanceServiceObject(Class.forName(className));
    }


}
