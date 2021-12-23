package cn.wsg.hello.spring.reflect.sort;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author Kingen
 */
public class SortUtil {

    /**
     * Order by property.
     */
    public static void sortByField(List<?> objectList, final String fieldSortBy) {
        objectList.sort(new Comparator() {
            public int compare(Object o1, Object o2) {
                try {
                    Field field = o1.getClass().getDeclaredField(fieldSortBy);
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (type == int.class) {
                        return ((Integer) field.get(o1)).compareTo((Integer) field.get(o2));
                    } else if (type == double.class) {
                        return Double.compare(field.getDouble(o1), field.getDouble(o2));
                    } else if (type == long.class) {
                        return Long.compare(field.getLong(o1), field.getLong(o2));
                    } else if (type == float.class) {
                        return Float.compare(field.getFloat(o1), field.getFloat(o2));
                    } else if (type == Date.class) {
                        return ((Date) field.get(o1)).compareTo((Date) field.get(o2));
                    } else if (isImplementsOf(type, Comparable.class)) {
                        return ((Comparable) field.get(o1)).compareTo(field.get(o2));
                    } else {
                        return String.valueOf(field.get(o1)).compareTo(String.valueOf(field.get(o2)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    public static boolean isImplementsOf(Class<?> clazz, Class<?> szInterface) {
        boolean flag = false;

        Class<?>[] face = clazz.getInterfaces();
        for (Class<?> c : face) {
            if (c == szInterface) {
                flag = true;
            } else {
                flag = isImplementsOf(c, szInterface);
            }
        }

        if (!flag && null != clazz.getSuperclass()) {
            return isImplementsOf(clazz.getSuperclass(), szInterface);
        }

        return flag;
    }
}
