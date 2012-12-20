package com.ks.cfxinterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Kevin Sapper (2012)
 */
public class ReflectionUtils {

    public static Set<Method> getMethods(Class<? extends Annotation> annotation) {
        Set<Method> methods = new HashSet<>();
        methods.addAll(Arrays.asList(ZKServer.class.getDeclaredMethods()));
        return methods;
    }
}
