package cn.com.clm.bean;

import cn.com.clm.anno.Table;
import cn.com.clm.anno.TableHeader;
import lombok.Data;

import java.io.Serializable;

/**
 * describe: phone
 *
 * @author liming.cao@hand-china.com
 */
@Data
@Table(type = "phone", desc = "所请求手机的数据")
public class Phone implements Serializable{

    private Long id;

    @TableHeader(index = 1, header = "类型")
    private String type;

    @TableHeader(index = 2, header = "机身颜色")
    private String color;

    @TableHeader(index = 3, header = "描述")
    private String desc;

}
