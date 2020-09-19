package cc.yound.common.core.exception;

import cc.yound.common.core.util.Code;
import org.springframework.http.HttpStatus;

/**
 * http 500
 */
public class FailedException extends HttpException {

    public FailedException() {
        this.code = Code.FAIL.getCode();
        this.httpStatusCode =  HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = Code.FAIL.getZhDescription();
    }

    public FailedException(int code) {
        this.code = code;
        this.httpStatusCode =  HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = Code.FAIL.getZhDescription();
    }
}
