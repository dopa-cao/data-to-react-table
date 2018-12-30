package cn.com.clm.util;

import cn.com.clm.error.ErrorEnum;
import cn.com.clm.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */
public class XmlUtil {

    public static Map<String,Class> xmlParse(String xmlName) throws CommonException {
        Map<String, Class> map = new HashMap<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(xmlName));
            Element element = document.getRootElement();
            Iterator iterator = element.elementIterator();
            while (iterator.hasNext()) {
                Element table = (Element)iterator.next();
                String type = table.element("type").getStringValue();
                String path = table.element("path").getStringValue();
                if (StringUtils.isBlank(type) || StringUtils.isBlank(path)) {
                    throw new CommonException(ErrorEnum.UNKNOWN_ERROR);
                }
                Class cls = Class.forName(path);
                map.put(type, cls);
            }
        } catch (Exception e) {
            throw new CommonException(ErrorEnum.UNKNOWN_ERROR,"xml解析出错");
        }
        return map;
    }

}
