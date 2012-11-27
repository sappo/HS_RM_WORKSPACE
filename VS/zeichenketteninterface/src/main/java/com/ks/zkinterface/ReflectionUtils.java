package com.ks.zkinterface;

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
    
    public static Set<Method> getMethods(Object object, Class<? extends Annotation> annotation) {
        Set<Method> methods = new HashSet<>();
        // search for methods with @ServerMethod annotation
        for (Method method : object.getClass().getMethods()) {
            for (Annotation methodAnnotation : method.getAnnotations()) {
                if(methodAnnotation.annotationType() == annotation) {
                    methods.add(method);
                }
            }
        }
        return methods;
    }

}
