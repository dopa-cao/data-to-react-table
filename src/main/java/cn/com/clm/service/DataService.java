package cn.com.clm.service;

import cn.com.clm.exception.CommonException;
import com.alibaba.fastjson.JSONObject;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
public interface DataService {

    JSONObject getData(String type) throws CommonException;

}
