package cc.young.common.mongodb.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动配置
 */
@Configuration
@EnableMongoRepositories(basePackages = {"cc.young"},repositoryFactoryBeanClass = CustomMongoRepositoryFactoryBean.class)
public class MongoAutoConfigure {

    @Autowired
    private MongoDatabaseFactory mongoDbFactory;

    @Autowired
    private MongoMappingContext mongoMappingContext;

    @Bean
    MongoTransactionManager transactionManager(){
        return new MongoTransactionManager(mongoDbFactory);
    }


    /**
     * 去除_class字段
     * @return
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter() {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        //此处是去除插入数据库的 _class 字段
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        // BigDecimal 与decimal128互转
        List<Converter<?, ?>> converterList = new ArrayList<>();
        converterList.add(new BigDecimalToDecimal128Converter());
        converterList.add(new Decimal128ToBigDecimalConverter());
        MongoCustomConversions mongoCustomConversions = new MongoCustomConversions(converterList);
        converter.setCustomConversions(mongoCustomConversions);
        return converter;
    }


}
