package cc.yound.common.core.exception;

import cc.yound.common.core.util.Code;
import org.springframework.http.HttpStatus;

/**
 * http 403
 */
public class ForbiddenException extends HttpException {
    public ForbiddenException() {
        this.code = Code.UN_AUTHENTICATION.getCode();;
        this.httpStatusCode = HttpStatus.FORBIDDEN.value();;
        this.message = Code.UN_AUTHENTICATION.getZhDescription();;
    }

    public ForbiddenException(Integer code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.FORBIDDEN.value();;
        this.message = Code.UN_AUTHENTICATION.getZhDescription();;
    }


}
