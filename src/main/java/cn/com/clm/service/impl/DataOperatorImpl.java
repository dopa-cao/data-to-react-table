package cn.com.clm.service.impl;

import cn.com.clm.service.data.DataOperator;
import cn.com.clm.util.DataUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
@Service
public class DataOperatorImpl extends DataOperator {

    @Override
    public List getOriginData(String type) {
        switch (type) {
            case "people":
                return DataUtil.getPeopleData();
            case "dog":
                return DataUtil.getDogData();
            case "phone":
                return DataUtil.getPhoneData();
                default:
                    return null;

        }
    }

}
