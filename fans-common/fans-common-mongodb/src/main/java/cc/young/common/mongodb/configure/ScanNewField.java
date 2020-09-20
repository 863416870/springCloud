package cc.young.common.mongodb.configure;

import java.lang.reflect.Field;
import java.util.Set;

import cc.young.common.mongodb.bean.InitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
/**
 * 启动时将表初始化
 *
 */
@Service
public class ScanNewField {
    // 写链接(写到主库,可使用事务)
    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.package}")
    private String packageName;

    @Autowired
    MongoMappingContext mongoMappingContext;

    @EventListener(ApplicationReadyEvent.class)
    public void scan() {
        if (StrUtil.isEmpty(packageName)) {
            return;
        }

        Set<Class<?>> set = ClassUtil.scanPackage(packageName);
        for (Class<?> clazz : set) {
            Document document = clazz.getAnnotation(Document.class);
            if (document == null) {
                continue;
            }
            // 创建表
            if (!mongoTemplate.collectionExists(clazz)) {
                mongoTemplate.createCollection(clazz);
                System.out.println("创建了" + clazz.getSimpleName() + "表");
            }

            // 创建索引
            IndexOperations indexOps = mongoTemplate.indexOps(clazz);
            IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
            resolver.resolveIndexFor(clazz).forEach(indexOps::ensureIndex);


            Field[] fields = ReflectUtil.getFields(clazz);
            for (Field field : fields) {
                // 获取注解
                if (field.isAnnotationPresent(InitValue.class)) {
                    InitValue defaultValue = field.getAnnotation(InitValue.class);
                    if (defaultValue.value() != null) {

                        // 更新表默认值
                        Query query = new Query();
                        query.addCriteria(Criteria.where(field.getName()).is(null));

                        Long count = mongoTemplate.count(query, clazz);
                        if (count > 0) {
                            Update update = new Update().set(field.getName(), defaultValue.value());
                            UpdateResult updateResult = mongoTemplate.updateMulti(query, update, clazz);

                            System.out.println(clazz.getSimpleName() + "表更新了" + updateResult.getModifiedCount() + "条默认值");
                        }
                    }
                }

            }

        }
    }

}