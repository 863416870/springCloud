package cc.yound.common.core.exception;

import cc.yound.common.core.util.Code;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * http 500
 */
@Getter
@Setter
public class HttpException extends RuntimeException {
    protected Integer code = Code.INTERNAL_SERVER_ERROR.getCode();
    protected Integer httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    protected String message = Code.INTERNAL_SERVER_ERROR.getZhDescription();

    public HttpException() {
    }

    public HttpException(Integer code, Integer httpStatusCode, String message) {
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public HttpException(Integer code, String message) {
        this.code = code;
        this.httpStatusCode =  HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }
}
