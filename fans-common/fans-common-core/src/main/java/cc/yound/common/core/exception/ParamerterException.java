package cc.yound.common.core.exception;

import cc.yound.common.core.util.Code;
import org.springframework.http.HttpStatus;

/**
 * 参数
 * http 404
 */
public class ParamerterException extends HttpException {

    public ParamerterException() {
        this.code = Code.PARAMETER_ERROR.getCode();
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
        this.message = Code.PARAMETER_ERROR.getZhDescription();
    }

    public ParamerterException(int code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
        this.message = Code.PARAMETER_ERROR.getZhDescription();
    }

    public ParamerterException(Integer code, String message) {
        this.code = code;
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }
}
