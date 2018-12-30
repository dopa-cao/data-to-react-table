package cn.com.clm.bean;

import cn.com.clm.anno.Table;
import cn.com.clm.anno.TableHeader;
import lombok.Data;

import java.io.Serializable;

/**
 * describe: dog
 *
 * @author liming.cao@hand-china.com
 */
@Data
@Table(type = "dog", desc = "所请求狗的数据")
public class Dog implements Serializable{

    private Long id;

    @TableHeader(index = 1, header = "名称")
    private String name;

    @TableHeader(index = 2, header = "毛色")
    private String color;

    @TableHeader(index = 3, header = "体高")
    private Double height;

    @TableHeader(index = 4, header = "描述")
    private String desc;

}
