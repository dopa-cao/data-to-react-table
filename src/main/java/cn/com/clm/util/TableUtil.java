package cn.com.clm.util;

import cn.com.clm.error.ErrorEnum;
import cn.com.clm.exception.CommonException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List; /**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
public class TableUtil {

    public static JSONArray buildTableHeader(List<String> descs, List<String> names) throws CommonException {
        if (descs.isEmpty() || names.isEmpty() || descs.size() != names.size()) {
            throw new CommonException(ErrorEnum.UNKNOWN_ERROR, "自定义注解配置错误");
        }
        JSONArray jsonArray = new JSONArray(descs.size());
        for (int i = 0; i < descs.size(); i++) {
            JSONObject jsonObject = buildTableHeaderObject(descs.get(i), names.get(i));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private static JSONObject buildTableHeaderObject(String title, String name) {
        JSONObject jsonObject = new JSONObject(3);
        jsonObject.put("title", title);
        jsonObject.put("dataIndex", name);
        jsonObject.put("key", name);
        return jsonObject;
    }
}
