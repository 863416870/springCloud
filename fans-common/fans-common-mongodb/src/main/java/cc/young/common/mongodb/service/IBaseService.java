package cc.young.common.mongodb.service;

import cc.young.common.mongodb.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IBaseService<T extends BaseEntity,ID extends Serializable> {

     void save(T t);

     void updateById(T t);

     void saveWithNull(T t);

     T selectById(ID id);

     void deleteById(ID id);

     List<T> selectBatchIds(Collection<ID> ids);

     void deleteBatchIds(Collection<ID> ids);
}
