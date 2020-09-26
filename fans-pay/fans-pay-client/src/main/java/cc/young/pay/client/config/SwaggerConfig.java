package cc.young.pay.client.config;

import cc.yound.common.core.config.BaseSwaggerConfig;
import cc.yound.common.core.properties.SwaggerProperties;
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
                .apiBasePackage("cc.young.pay.client.controller")
                .title("支付平台")
                .description("支付平台相关接口文档")
                .contactName("fans")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
