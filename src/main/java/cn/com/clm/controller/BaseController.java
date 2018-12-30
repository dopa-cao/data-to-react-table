package cn.com.clm.controller;

import cn.com.clm.constant.Constant;
import cn.com.clm.error.ErrorEnum;
import cn.com.clm.exception.CommonException;
import cn.com.clm.response.CommonReturnData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * describe:
 *
 * @author liming.cao
 */
public abstract class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception exception) {
        Map<String, Object> response = new HashMap<>(2);
        if (exception instanceof CommonException) {
            CommonException commonException = (CommonException)exception;
            response.put(Constant.ERROR_CODE, commonException.getErrorCode());
            response.put(Constant.ERROR_MSG, commonException.getErrorMsg());
        } else {
            response.put(Constant.ERROR_CODE, ErrorEnum.UNKNOWN_ERROR.getErrorCode());
            response.put(Constant.ERROR_MSG, ErrorEnum.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnData.create(response, Constant.REQUEST_FAIL);
    }


}
