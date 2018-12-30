package cn.com.clm.bean;

import cn.com.clm.error.ErrorEnum;
import cn.com.clm.exception.CommonException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
@Component
public class DataConfig {

    @Value("#{'${data.type:\"\"}'.split(',')}")
    private String[] types;
    @Value("#{'${data.path:\"\"}'.split(',')}")
    private String[] paths;

    public Map<String, Class> getDataConfig() throws CommonException {
        Map<String, Class> map = new HashMap<>();
        if (null == this.paths || null == this.types ||
                this.paths.length != this.types.length) {
            throw new CommonException(ErrorEnum.UNKNOWN_ERROR,"配置有误");
        }
        try {
            for (int i = 0; i < types.length; i++) {
                Class cls = Class.forName(this.paths[i]);
                map.put(types[i], cls);
            }
        }catch (Exception e) {
            throw new CommonException(ErrorEnum.UNKNOWN_ERROR,"配置加载类失败");
        }
        return map;
    }

}
