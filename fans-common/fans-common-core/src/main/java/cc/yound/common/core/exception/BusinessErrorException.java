package cc.yound.common.core.exception;

import cc.yound.common.core.util.Code;
import org.springframework.http.HttpStatus;

/**
 * http 500
 */
public class BusinessErrorException extends HttpException {

    public BusinessErrorException(String message) {
        this.code = Code.BUSINESS_ERROR.getCode();
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }
}
