package cc.yound.common.core.exception;


import cc.yound.common.core.util.Code;
import org.springframework.http.HttpStatus;

/**
 * http 400
 */
public class NotFoundException extends HttpException {

    public NotFoundException() {
        this.code = Code.NOT_FOUND.getCode();
        this.httpStatusCode = HttpStatus.NOT_FOUND.value();
        this.message = Code.NOT_FOUND.getZhDescription();
    }
    public NotFoundException(Integer code) {
        this.code = code;
    }
}
