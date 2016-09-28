package homework.lesson6.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Anatoly on 20.09.2016.
 */
public class HomeReflection {

    public void printAllMethods(Class c) {
        while (c != null) {
            for (Method m: c.getDeclaredMethods()) {
                System.out.println(m.getName());
            }
            c = c.getSuperclass();
        }
    }

    public void printAllGetters(Class c) {
        for (Field f : c.getDeclaredFields()) {
            String getterName = "get" + getFirstSymbolUpperCase(f.getName());
            for (Method m: c.getDeclaredMethods()) {
                if (m.getName().contains(getterName)) {
                    System.out.println(m.getName());
                    break;
                }
            }
        }
    }

    public boolean checkStringConstantsEqualNames(Class c) {
        for (Field f: c.getDeclaredFields() ) {
            if (isConstant(f) && isString(f) && !isEqualConstantsNames(f)) {
                return false;
            }
        }
        return true;
    }

    private boolean isEqualConstantsNames(Field f) {
        try {
            return f.getName() == f.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isString(Field f) {
        return f.getType() == String.class;
    }

    private boolean isConstant(Field f) {
        return Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers());
    }

    private String getFirstSymbolUpperCase(String f) {
        return f.substring(0,1).toUpperCase() + f.substring(1);
    }
}
