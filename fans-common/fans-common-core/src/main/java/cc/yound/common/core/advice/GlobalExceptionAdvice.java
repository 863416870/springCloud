package cc.yound.common.core.advice;

import cc.yound.common.core.config.AbstractExceptionCodeSource;
import cc.yound.common.core.exception.HttpException;
import cc.yound.common.core.util.Code;
import cc.yound.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @Autowired(required = false)
    private AbstractExceptionCodeSource exceptionCode;

    /**
     * 捕获未知异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleException(HttpServletRequest req, Exception e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        System.out.println(e);
        log.error("[handleException]全局异常Exception拦截 ex: [{}]", e);

        return R.builder()
                .code(Code.INTERNAL_SERVER_ERROR.getCode())
                .msg(Code.INTERNAL_SERVER_ERROR.getZhDescription())
                .requestUri(method + " " + requestUrl)
                .build();
    }

    /**
     * 捕获自定义异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<R> handleHttpException(HttpServletRequest req, HttpException e) {
        log.error("[handleHttpException] 全局拦截自定义异常: [{}]", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        R.RBuilder<Object> builder = R.builder()
                .requestUri(method + " " + requestUrl)
                .code(e.getCode());
        if(null == exceptionCode || null == exceptionCode.getMessage(e.getCode())){
            log.warn("ex : {} ", exceptionCode);
            log.warn("请配置自己的业务错误码....");
            log.warn("当前异常 {}",e.getMessage());
            builder.msg(e.getMessage());
        }else{
            builder.msg(exceptionCode.getMessage(e.getCode()));
        }

        R r = builder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        ResponseEntity<R> res = new ResponseEntity<>(r, headers, httpStatus);
        return res;
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, MissingServletRequestParameterException e) {
        log.error("Missing Request Parameter: [{}]", e.getMessage(), e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return R.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(message)
                .requestUri(method + " " + requestUrl)
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
        log.error("Method Argument Type Mismatch :[{}]", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return R.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(message)
                .requestUri(method + " " + requestUrl)
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error("Method Argument Not Valid :[{}]", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s%s", error.getField(), error.getDefaultMessage());

        return R.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(message)
                .requestUri(method + " " + requestUrl)
                .build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, BindException e) {
        log.error("Bind Exception :[{}]", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return R.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(message)
                .requestUri(method + " " + requestUrl)
                .build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, NoHandlerFoundException e) {
        log.error("404 Not Found", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        return R.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .msg(e.getMessage())
                .requestUri(method + " " + requestUrl)
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, HttpMessageNotReadableException e) {
        log.error("Message Not Readable", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        return R.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(e.getMessage())
                .requestUri(method + " " + requestUrl)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, ConstraintViolationException e) {
        log.error("ConstraintViolationException exception", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        String message = "";
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            message = constraintViolation.getMessage();
            break;
        }

        return R.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(message)
                .requestUri(method + " " + requestUrl)
                .build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.error("Request Method Not Supported", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        return R.builder()
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .msg(e.getMessage())
                .requestUri(method + " " + requestUrl)
                .build();
    }

    /**
     * 文件类型错误
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public R handleError(HttpServletRequest req, HttpMediaTypeNotSupportedException e) {
        log.error("Media Type Not Supported", e);
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        return R.builder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .msg(e.getMessage())
                .requestUri(method + " " + requestUrl)
                .build();
    }

}
