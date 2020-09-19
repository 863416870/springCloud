package cc.yound.common.core.exception;

import cc.yound.common.core.util.Code;
import org.springframework.http.HttpStatus;

/**
 * 401
 */
public class TokenException extends HttpException {

    public TokenException() {
        this.code = Code.UN_AUTHENTICATION.getCode();
        this.httpStatusCode =  HttpStatus.UNAUTHORIZED.value();
        this.message = Code.UN_AUTHENTICATION.getZhDescription();
    }

    public TokenException(int code) {
        this.code = code;
        this.httpStatusCode =  HttpStatus.UNAUTHORIZED.value();
        this.message = Code.UN_AUTHENTICATION.getZhDescription();
    }
}
