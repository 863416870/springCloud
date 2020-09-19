package cc.yound.common.core.exception;

import cc.yound.common.core.util.Code;
import org.springframework.http.HttpStatus;

/**
 * http 500
 */
public class ServerErrorException extends HttpException {

    public ServerErrorException() {
        this.code = Code.INTERNAL_SERVER_ERROR.getCode();
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = Code.INTERNAL_SERVER_ERROR.getZhDescription();
    }
    public ServerErrorException(int code){
        this.code = code;
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = Code.INTERNAL_SERVER_ERROR.getZhDescription();
    }
}
