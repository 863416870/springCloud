package cc.young.common.mongodb.dao;

import cc.young.common.mongodb.entity.BaseEntity;
import cc.young.common.mongodb.entity.PageResult;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;

import java.io.Serializable;
import java.util.List;


public interface BaseRepositoryEnhance <T extends BaseEntity, ID extends Serializable> {

    /**
     * 分页查询
     * @param t
     * @param includeFields 指定字段
     * @param excludeFields 排除字段
     * @return
     */
    PageResult<T> findPage(T t,List<String> includeFields,List<String> excludeFields);

    /**
     * 获取当前泛型的实体信息
     * @return
     */
    MongoEntityInformation<T, ID> getCurEntityInformation();

    /**
     * 列表查询
     * @param t
     * @return
     */
    List<T> findList(T t);
}
