package cc.young.pay.client.config;

import com.yzy.common.core.config.BaseSwaggerConfig;
import com.yzy.common.core.dto.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.yzy.pay.client.controller")
                .title("支付平台")
                .description("支付平台相关接口文档")
                .contactName("yzy")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
