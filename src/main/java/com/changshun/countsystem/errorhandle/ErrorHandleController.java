package com.changshun.countsystem.errorhandle;

import com.changshun.countsystem.common.CodeStatus;
import com.changshun.countsystem.common.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
* 统一异常处理
* */
//@RestControllerAdvice
public class ErrorHandleController {

    @ExceptionHandler(Exception.class)
    public ResponseVo errortip(){
        return ResponseVo.error(CodeStatus.EXCEPTION_TIP.getCode(), CodeStatus.EXCEPTION_TIP.getTip());
    }

}
