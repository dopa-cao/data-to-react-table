package cn.com.clm.bean;

import cn.com.clm.anno.Table;
import cn.com.clm.anno.TableHeader;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * describe: people
 *
 * @author liming.cao@hand-china.com
 */
@Data
@Table(type = "people", desc = "所请求人的数据")
public class People implements Serializable{

    private Long id;

    @TableHeader(index = 1, header = "姓名")
    private String name;

    @TableHeader(index = 2, header = "毕业学校")
    private String school;

    @JSONField(format = "yyyy-MM-dd")
    @TableHeader(index = 3, header = "出生日期")
    private Date birth;

    @TableHeader(index = 4, header = "描述")
    private String desc;

}
