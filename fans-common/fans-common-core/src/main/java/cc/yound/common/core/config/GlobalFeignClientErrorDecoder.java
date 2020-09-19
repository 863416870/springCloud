package cc.yound.common.core.config;

import cc.yound.common.core.exception.HttpException;
import cc.yound.common.core.util.R;
import cn.hutool.json.JSONUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@Slf4j
public class GlobalFeignClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String message;
        try {
            message = Util.toString(response.body().asReader());
            R r = JSONUtil.toBean(message, R.class);
            if (response.status() >= 400 && response.status() <= 500) {
                throw new HttpException(r.getCode(),response.status(),r.getMsg());
            }
        } catch (IOException e) {
            log.error("client.IOException", e);
        }
        return null;
    }
}
