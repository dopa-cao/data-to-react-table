package cn.com.clm.service.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
public enum FastCodeEnum{

    FAST_CODE_ONE("FAST_CODE_SCHOOL", "学校", "school"),
    FAST_CODE_TWO("FAST_CODE_COLOR", "颜色", "color"),
    ;

    private String fastKey;
    private String cnDesc;
    private String enDesc;

    FastCodeEnum(String fastKey, String cnDesc, String enDesc) {
        this.fastKey = fastKey;
        this.cnDesc = cnDesc;
        this.enDesc = enDesc;
    }

    public static FastCodeEnum getFastCode(String enDesc) {
        for (FastCodeEnum fc : FastCodeEnum.values()) {
            if (StringUtils.equals(fc.getEnDesc(), enDesc)) return fc;
        }
        return null;
    }

    public String getFastKey() {
        return fastKey;
    }

    public String getCnDesc() {
        return cnDesc;
    }

    public String getEnDesc() {
        return enDesc;
    }


    public static JSONArray getFastCodeByType(String[] keyDesc) {
        JSONArray jsonArray = new JSONArray(keyDesc.length);
        for (String enDesc : keyDesc) {
            FastCodeEnum fastCode = getFastCode(enDesc);
            if (null != fastCode) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", enDesc);
                jsonObject.put("code", fastCode.getFastKey());
                jsonObject.put("desc", fastCode.getCnDesc());
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }
}
