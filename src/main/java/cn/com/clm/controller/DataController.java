package cn.com.clm.controller;

import cn.com.clm.exception.CommonException;
import cn.com.clm.response.CommonReturnData;
import cn.com.clm.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @author liming.cao@hand-china.com
 */

@Api("test table data")
@RestController
@RequestMapping(value = "/v1")
public class DataController extends BaseController{

    @Autowired
    private DataService dataService;


    @ApiOperation("获取表格数据")
    @GetMapping("/getTableData")
    public ResponseEntity<CommonReturnData> getData(@RequestParam("type") String type) throws CommonException {
        return ResponseEntity.ok(CommonReturnData.create(dataService.getData(type)));
    }



}
