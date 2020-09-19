package cc.young.common.mongodb.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class FieldCacheUtil {
    public static final Map<Class<?>, Field> idFieldMap = new ConcurrentHashMap<>();
    public static final Map<Class<?>, Map<String, Field>> fieldMap = new ConcurrentHashMap<>();

    public FieldCacheUtil() {
    }

    @SneakyThrows
    public static <T, ID> ID getId(T t) {
        Class cla = t.getClass();
        try {
            Field primaryKeyField = getPrimaryKeyField(cla);
            return (ID) primaryKeyField.get(t);
        } catch (IllegalAccessException var3) {
            throw new Exception("获取id失败");
        }
    }

    @SneakyThrows
    public static Field getPrimaryKeyField(Class<?> tc) {
        Field idField = idFieldMap.getOrDefault(tc, null);
        if (Objects.nonNull(idField)) {
            return idField;
        } else {
            Field[] allFields = tc.getDeclaredFields();
            Field[] var3 = allFields;
            int var4 = allFields.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Field field = var3[var5];
                Id tableId = field.getDeclaredAnnotation(Id.class);
                if (Objects.nonNull(tableId)) {
                    field.setAccessible(true);
                    idFieldMap.putIfAbsent(tc, field);
                    return field;
                }
            }

            throw new Exception("当前实体没有主键");
        }
    }

    public static <T> Map<String, Field> getFieldMap(Class<T> entityClass) {

        Map<String, Field> classFieldMap = fieldMap.get(entityClass);
        if (classFieldMap != null) {
            return classFieldMap;
        }
        Field[] fields = ReflectUtil.getFieldsDirectly(entityClass, false);
        classFieldMap = MapUtil.newHashMap(fields.length);
        for (Field field : fields) {
            classFieldMap.put(field.getName(), field);
        }
        fieldMap.put(entityClass,classFieldMap);
        return classFieldMap;
    }

    public static Method hasCreateTimeMethod(Class<?> cla, String methodName) {
        Method[] methods = cla.getDeclaredMethods();
        Method[] var3 = methods;
        int var4 = methods.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Method met = var3[var5];
            if (methodName.equals(met.getName())) {
                Class<?>[] paramsCla = met.getParameterTypes();
                if (paramsCla != null && paramsCla.length == 1) {
                    Class<?> firstCla = paramsCla[0];
                    if ("java.util.Date".equals(firstCla.getCanonicalName())) {
                        return met;
                    }

                }
            }
        }

        return null;
    }
}
