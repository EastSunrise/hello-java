package wsg.java.spring.reflect;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Kingen
 */
public class ReflectUtils {

    public static final List<Class<?>> BASE_CLASS = Arrays.asList(new Class<?>[]{
            int.class, double.class, long.class, float.class, Date.class, String.class
    });


    /**
     * 获取对象属性及值
     */
    public static Map<String, Object> convertObject2Map(Object object) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (null != field.get(object)) {
                    map.put(field.getName(), field.get(object));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取对象及父类对象属性及值
     */
    public static Map<String, Object> convertObject2MapWithSuper(Object object) {
        Map<String, Object> map = new HashMap<>();
        Class<?> curClass = object.getClass();
        while (null != curClass) {
            for (Field field : curClass.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (null != field.get(object)) {
                        map.put(field.getName(), field.get(object));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            curClass = curClass.getSuperclass();
        }
        return map;
    }

    /**
     * 获取对象的属性及值，属性若非基本类型，递归获取其属性及值
     */
    public static Map<String, Object> convertObject2MapWithObject(Object object) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> type = field.getType();
            try {
                Object fieldObj = field.get(object);
                if (null != fieldObj) {
                    if (BASE_CLASS.contains(type)) {
                        map.put(field.getName(), fieldObj);
                    } else {
                        map.putAll(convertObject2Map(fieldObj));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
