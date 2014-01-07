package org.vintsie.jcobweb.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vintsie.jcobweb.service.interfaces.CommonSV;

/**
 * Created with IntelliJ IDEA.
 * User: dev001
 * Date: 12/23/13
 * Time: 3:45 PM
 */
public class CommonSVImpl implements CommonSV {

    private transient static Log log = LogFactory.getLog(CommonSVImpl.class);

    @Override
    public long getCurrentDateTime() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("CommonSVImpl.class was called at " + System.currentTimeMillis());
        }
        return System.currentTimeMillis();
    }

    @Override
    public String sayHelloToWorld(String name, int times) throws Exception {
        if (log.isInfoEnabled()) {
            log.info(name + " said hello to world just now.");
        }
        return "thanks";
    }
}
