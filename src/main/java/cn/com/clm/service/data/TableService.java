package cn.com.clm.service.data;

import com.alibaba.fastjson.JSONArray;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
public interface TableService {

    /**
     * 获取快码
     *
     * @param keyDesc   desc
     * @return          result
     */
    JSONArray getFastCodeByType(String []keyDesc);

}
