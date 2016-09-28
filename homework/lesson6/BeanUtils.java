package homework.lesson6.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Anatoly on 22.09.2016.
 */
class BeanUtils {
    void assign(Object from, Object to) {

        ArrayList<Method> allGetters = getAllGetters(from.getClass());
        ArrayList<Method> allSetters = getAllSetters(to.getClass());

        for (Method getter : allGetters) {
            if (!isMethodPublic(getter)) {
                continue;
            }
            for (Method setter : allSetters) {
                if (isMethodPublic(setter) &&
                        isGetterSetterValueComparable(getter, setter) && isGetterSetterFromSameField(getter, setter)) {
                    try {
                        setter.invoke(to, getter.invoke(from));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean isMethodPublic(Method m) {
        return Modifier.isPublic(m.getModifiers());
    }

    private String getFirstSymbolUpperCase(String f) {
        return f.substring(0,1).toUpperCase() + f.substring(1);
    }

    private ArrayList<Method> getAllGetters(Class c) {
        return getMethods("get", c);
    }

    private ArrayList<Method> getAllSetters(Class c) {
        return getMethods("set", c);
    }

    private ArrayList<Method> getMethods(String actionName, Class c) {
        ArrayList<Method> methods = new ArrayList<>();

        for (Field f : c.getDeclaredFields()) {
            String methodName = actionName + getFirstSymbolUpperCase(f.getName());
            for (Method m: c.getDeclaredMethods()) {
                if (m.getName().contains(methodName)) {
                    methods.add(m);
                }
            }
        }

        return methods;
    }

    private boolean isGetterSetterFromSameField(Method getter, Method setter) {
        String tempGetterName = setter.getName().replace("set", "get");
        return Objects.equals(getter.getName(), tempGetterName);
    }

    private boolean isGetterSetterValueComparable (Method getter, Method setter) {
        return isClassesComparable(getGetterReturnType(getter), getSetterParameterType(setter));
    }

    private Class<?> getGetterReturnType(Method getter) {
        return getter.getReturnType();
    }

    private Class<?> getSetterParameterType(Method setter) {
        return (Class)setter.getParameters()[0].getParameterizedType();
    }

    private boolean isClassesComparable (Class clazz, Class superclazz) {
        while (superclazz != null) {
            if (clazz == superclazz) {
                return true;
            }
            superclazz = superclazz.getSuperclass();
        }
        return false;
    }
}
