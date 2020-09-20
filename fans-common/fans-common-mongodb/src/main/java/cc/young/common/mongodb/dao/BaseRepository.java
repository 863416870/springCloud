package cc.young.common.mongodb.dao;

import cc.young.common.mongodb.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 自定义Repository
 * @param <T> 实体类的泛型
 * @param <ID> id的泛型
 */
@NoRepositoryBean//告诉JPA不要创建对应接口的bean对象
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends MongoRepository<T, ID>,BaseRepositoryEnhance<T, ID>  {

}
