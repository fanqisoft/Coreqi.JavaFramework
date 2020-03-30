package cn.coreqi.core.exception;

import cn.coreqi.core.ProjectException;
import cn.coreqi.core.ResWrapper;
import cn.coreqi.core.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //错误信息构建器
    @Autowired
    private ErrorInfoBuilder errorInfoBuilder;

    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("该数据有关联数据，操作失败!");
        }
        return RespBean.error("数据库异常，操作失败!");
    }

    //根据业务规则,统一处理异常。
    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, Exception error) {

        ErrorInfo errorInfo = errorInfoBuilder.getErrorInfo(request, error);

        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header);

        //业务异常,返回200,前端判断错误,进行处理
        if (error instanceof ProjectException) {
            errorInfo.setStatusCode(HttpStatus.OK.value());
            ResWrapper resWrapper = ResWrapper.BusinessError(error.getMessage(), ((ProjectException) error).getMessageList());
            return ResponseEntity.status(errorInfo.getStatusCode()).body(resWrapper);
        }

        //显式捕捉404
        if (error instanceof NoHandlerFoundException) {
            errorInfo.setStatusCode(HttpStatus.NOT_FOUND.value());
        }


        //显式捕捉403 org.springframework.security.access.AccessDeniedException
        if (error instanceof AccessDeniedException) {
            errorInfo.setStatusCode(HttpStatus.FORBIDDEN.value());
        }

        //其他异常直接返回
        return ResponseEntity.status(errorInfo.getStatusCode()).body(errorInfo);
    }
}
