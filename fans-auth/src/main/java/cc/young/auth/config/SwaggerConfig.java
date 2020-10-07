package cc.young.auth.config;

import cc.young.common.swagger.config.BaseSwaggerConfig;
import cc.young.common.swagger.properties.SwaggerProperties;
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
                .apiBasePackage("cc.young.auth.controller")
                .title("认证中心")
                .description("认证中心相关接口文档")
                .contactName("fans")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
