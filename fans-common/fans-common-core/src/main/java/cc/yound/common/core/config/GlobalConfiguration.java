package cc.yound.common.core.config;

import cc.yound.common.core.advice.GlobalExceptionAdvice;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfiguration {
    /**
     * 通用全局异常拦截
     * @return
     */
    @Bean
    public GlobalExceptionAdvice exceptionAdvice()
    {
        return new GlobalExceptionAdvice();
    }

    /**
     * 通用全局拦截器
     * @return
     */
    @Bean
    public WebMvcConfiguration globalWebConfig(){
        return new WebMvcConfiguration();
    }

    /**
     * feign 传输异常自定义
     * @return
     */
    @Bean
    public GlobalFeignClientErrorDecoder feignClientErrorDecoder(){
        return new GlobalFeignClientErrorDecoder();
    }

    /**
     * 定义feign请求日志级别
     * @return
     */
    @Bean
    Logger.Level getLogger(){
        return Logger.Level.BASIC;
    }
    
}
