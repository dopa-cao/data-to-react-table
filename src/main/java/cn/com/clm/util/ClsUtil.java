package cn.com.clm.util;

import cn.com.clm.anno.TableHeader;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
public class ClsUtil {

    public static String[] getFieldsFromCls(Class cls) {
        TreeMap<Integer, String> treeMap = classPropertyAsMap(cls);
        String[] names = new String[treeMap.size()];
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            names[entry.getKey() - 1] = entry.getValue();
        }
        return names;
    }

    public static String[] getDescsFromCls(Class cls) {
        TreeMap<Integer, String> treeMap = filedDescAsMap(cls);
        String[] descs = new String[treeMap.size()];
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            descs[entry.getKey() - 1] = entry.getValue();
        }
        return descs;
    }

    private static TreeMap<Integer,String> filedDescAsMap(Class cls) {
        TreeMap<Integer, String> map = new TreeMap<>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            TableHeader tableHeader =  field.getAnnotation(TableHeader.class);
            if (null == tableHeader) {
                continue;
            }
            if (!StringUtils.isBlank(tableHeader.header())) {
                map.put(tableHeader.index(), tableHeader.header());
            }
        }
        return map;
    }

    private static TreeMap<Integer,String> classPropertyAsMap(Class cls) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            TableHeader tableHeader = field.getAnnotation(TableHeader.class);
            if (null == tableHeader) {
                continue;
            }
            treeMap.put(tableHeader.index(), field.getName());
        }
        return treeMap;
    }
}
