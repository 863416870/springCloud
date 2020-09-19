package cc.young.common.util;

import cn.hutool.core.util.ReflectUtil;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class EnumUtils {
    private EnumUtils(){

    }


    /**
     * 获得枚举名对应指定字段值的Map<br>
     *
     * @param clazz     枚举类
     * @param valueName 字段名，最终调用getXXX方法
     * @param fieldName 字段名，最终调用getXXX方法
     * @return 枚举名对应指定字段值的Map
     */
    public  static  Map<Integer, String> convert2Map(Class<? extends Enum<?>> clazz,String fieldName, String valueName){
        final Enum<?>[] enums = clazz.getEnumConstants();
        if (null == enums) {
            return Collections.emptyMap();
        }
        final LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        for (Enum<?> e : enums) {
            map.put( (Integer)ReflectUtil.getFieldValue(e, fieldName), (String)ReflectUtil.getFieldValue(e, valueName));
        }
        return map;
    }
}
