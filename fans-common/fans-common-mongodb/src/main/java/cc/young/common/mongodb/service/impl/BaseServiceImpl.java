package cc.young.common.mongodb.service.impl;

import cc.young.common.mongodb.dao.BaseRepository;
import cc.young.common.mongodb.entity.BaseEntity;
import cc.young.common.mongodb.service.IBaseService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements IBaseService<T, ID> {

    @Autowired
    protected BaseRepository<T, ID> baseRepository;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void save(T t) {
        ID id = baseRepository.getCurEntityInformation().getId(t);
        if (StringUtils.isEmpty(id)) {
            ReflectUtil.setFieldValue(t,baseRepository.getCurEntityInformation().getIdAttribute(),null);
            baseRepository.save(t);
        } else {
            T dbObj = this.selectById(id);
            CopyOptions copyOptions = CopyOptions.create().ignoreNullValue();
            BeanUtil.copyProperties(t,dbObj,copyOptions);
            baseRepository.save(dbObj);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateById(T t) {
        ID id = baseRepository.getCurEntityInformation().getId(t);
        if (Objects.isNull(id)) {
            return;
        }
        baseRepository.save(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveWithNull(T t) {
        baseRepository.save(t);
    }

    @Override
    public T selectById(ID id) {
        Optional<T> dbObj = baseRepository.findById(id);
        return dbObj.orElse(null);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    @SneakyThrows
    public List<T> selectBatchIds(Collection<ID> ids) {
        return CollectionUtil.newArrayList(baseRepository.findAllById(ids));
    }

    @Override
    public void deleteBatchIds(Collection<ID> ids) {
        Iterable<T> allById = baseRepository.findAllById(ids);
        baseRepository.deleteAll(allById);
    }
}
