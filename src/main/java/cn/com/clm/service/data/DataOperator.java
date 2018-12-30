package cn.com.clm.service.data;

import cn.com.clm.bean.DataConfig;
import cn.com.clm.constant.Constant;
import cn.com.clm.error.ErrorEnum;
import cn.com.clm.exception.CommonException;
import cn.com.clm.service.DataService;
import cn.com.clm.util.ClsUtil;
import cn.com.clm.util.ScanUtil;
import cn.com.clm.util.TableUtil;
import cn.com.clm.util.XmlUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
@Component
public abstract class DataOperator implements DataService{

    @Autowired
    private DataConfig dataConfig;

    @Override
    public JSONObject getData(String type) throws CommonException {
        JSONObject jsonObject = new JSONObject();
//        Class cls = ScanUtil.getClsMap(Constant.PACKAGE_PATH).get(type);
//        Class cls = XmlUtil.xmlParse(Constant.XML_NAME).get(type);
        Class cls = dataConfig.getDataConfig().get(type);
        if (null == cls) {
            throw new CommonException(ErrorEnum.IMPORT_ERROR);
        }
        String []fieldNames = ClsUtil.getFieldsFromCls(cls);
        String []descsNames = ClsUtil.getDescsFromCls(cls);
        JSONArray headers = TableUtil.buildTableHeader(Arrays.asList(descsNames), Arrays.asList(fieldNames));
        JSONArray fastCodes = FastCodeEnum.getFastCodeByType(fieldNames);
        jsonObject.put("header", headers);
        jsonObject.put("row", getOriginData(type));
        jsonObject.put("fastCode", fastCodes);
        return jsonObject;
    }

    public abstract List getOriginData(String type);
}
