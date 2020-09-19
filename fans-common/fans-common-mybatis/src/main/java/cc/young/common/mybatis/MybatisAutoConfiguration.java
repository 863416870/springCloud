package cc.young.common.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: MybatisAutoConfiguration
 * date: 2020/9/19 14:03
 * author: faner
 */
@Configuration(proxyBeanMethods = false)
public class MybatisAutoConfiguration {

    /**
     * 分页插件
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}