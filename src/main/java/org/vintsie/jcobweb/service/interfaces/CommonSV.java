package org.vintsie.jcobweb.service.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 3:45 PM
 */
public interface CommonSV {

    /**
     * get current datetime. <code>System.currentTimeMillis()
     * </code>
     *
     * @return time <code>System.currentTimeMillis()</code>
     * @throws Exception
     */
    public long getCurrentDateTime() throws Exception;

    public String sayHelloToWorld(String name, int times) throws Exception;

}
