package com.ks.zk.zkserver;

import com.ks.zk.zkserver.util.ReflectionUtils;
import com.ks.zk.zkserver.util.ServerMethod;
import com.ks.zkinterface.ZKServer;
import java.lang.reflect.Method;
import org.apache.commons.lang.StringUtils;

/**
 * Implementation of string manipulation server.
 *
 * @author Kevin Sapper (2012)
 */
public class ZKServerImpl implements ZKServer {

    @Override
    public String verdoppeln(String text, int quantity) {
        return StringUtils.repeat(text, quantity);
    }

    @Override
    public String zeichenweisesVerdoppeln(String text, int quantity) {
        StringBuffer sb = new StringBuffer(text.length() * quantity);
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < quantity; j++) {
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String spiegeln(String text) {
        return StringUtils.reverse(text);
    }

    @Override
    public int laenge(String text) {
        return StringUtils.length(text);
    }

    @ServerMethod
    public String options() {
        StringBuffer options = new StringBuffer();
        options.append("[METHOD] options()\n");
        for (Method method : ReflectionUtils.getMethods(this, ServerMethod.class)) {
            options.append("> ").append(method.getName()).append("(");
            for (Class<?> paramType : method.getParameterTypes()) {
                options.append(paramType.getSimpleName()).append(",");
            }
            if (options.charAt(options.length() - 1) == ',') {
                options.deleteCharAt(options.length() - 1);
            }
            options.append(")\n");
        }
        return options.toString();
    }
}