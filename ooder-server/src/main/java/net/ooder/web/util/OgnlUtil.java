package net.ooder.web.util;

import net.ooder.context.JDSActionContext;
import net.ooder.jds.core.esb.util.JDSConverter;
import net.ooder.jds.core.esb.util.OgnlValueStack;
import net.ooder.server.httpproxy.core.HttpRequest;
import ognl.OgnlContext;

public class OgnlUtil {


    public static OgnlContext getOgnlContext() {
        OgnlContext ognlContext = null;
        HttpRequest request = (HttpRequest) JDSActionContext.getActionContext().getHttpRequest();
        if (request != null) {
            ognlContext = request.getOgnlContext();
        } else {
            ognlContext = new OgnlContext(OgnlValueStack.getAccessor(), JDSConverter.getInstance(), null, JDSActionContext.getActionContext().getContext());
        }
        return ognlContext;
    }

}
