package wsg.java.fundamental.reflection;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Example for {@link java.lang.reflect}
 *
 * @author Kingen
 */
public class ReflectionExample {

    public static void main(String[] args) {
        System.out.println(toString(new ReflectionExample()));
        System.out.println(printClass(ReflectionExample.class));
    }

    /**
     * Convert an object to a string.
     */
    public static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        return toString(object, new HashSet<>());
    }

    private static String toString(Object object, Set<Object> visited) {
        if (visited.contains(object)) {
            return "...";
        }

        Class<?> clazz = object.getClass();
        if (String.class.equals(clazz)) {
            return (String) object;
        }

        StringBuilder builder = new StringBuilder();
        if (clazz.isArray()) {
            Class<?> componentType = clazz.getComponentType();
            builder.append(componentType).append("[]{");
            for (int i = 0; i < Array.getLength(object); i++) {
                if (i > 0) {
                    builder.append(", ");
                }
                Object value = Array.get(object, i);
                if (componentType.isPrimitive()) {
                    builder.append(value);
                    continue;
                }
                builder.append(toString(value));
            }
            builder.append("}");
            return builder.toString();
        }

        while (!Object.class.equals(clazz)) {
            builder.append(clazz.getSimpleName()).append("[");
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(field.getName()).append("=");
                Class<?> type = field.getType();
                Object value = null;
                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (type.isPrimitive()) {
                    builder.append(value);
                    continue;
                }
                builder.append(toString(value));
            }
            builder.append("]");
            clazz = clazz.getSuperclass();
        }

        return builder.toString();
    }

    /**
     * Print the structure of the specified class.
     */
    public static String printClass(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(clazz.getPackageName()).append(";\n\n");
        builder.append(Modifier.toString(clazz.getModifiers())).append(" class ").append(clazz.getSimpleName());
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && !superclass.equals(Object.class)) {
            builder.append(" extend ").append(superclass.getSimpleName());
        }
        builder.append(" {\n");
        builder.append(printConstructors(clazz));
        builder.append("\n");
        builder.append(printMethods(clazz));
        builder.append("\n");
        builder.append(printFields(clazz));
        builder.append("}");
        return builder.toString();
    }

    private static String printFields(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()) {
            builder.append(Modifier.toString(field.getModifiers())).append(" ")
                    .append(field.getType().getSimpleName()).append(" ")
                    .append(field.getName()).append(";\n");
        }
        return builder.toString();
    }

    private static String printMethods(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        for (Method method : clazz.getDeclaredMethods()) {
            builder.append("\t").append(Modifier.toString(method.getModifiers())).append(" ")
                    .append(method.getReturnType().getSimpleName()).append(" ")
                    .append(method.getDeclaringClass().getSimpleName()).append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(parameterTypes[i].getSimpleName());
            }
            builder.append(");\n");
        }
        return builder.toString();
    }

    private static String printConstructors(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            builder.append("\t").append(Modifier.toString(constructor.getModifiers())).append(" ")
                    .append(constructor.getDeclaringClass().getSimpleName()).append("(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(parameterTypes[i].getSimpleName());
            }
            builder.append(");\n");
        }
        return builder.toString();
    }
}
